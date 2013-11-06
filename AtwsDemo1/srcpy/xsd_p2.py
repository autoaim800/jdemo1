'''
Created on 05/11/2013

@author: bill

compile relationship between each element
'''
import sys
import os
import lxml.etree

def info(s):print(s)
def error(s):print(s)

class MySimpleType:
    def __init__(self, name, baseType, maxLength):
        self.name = name
        self.base = baseType
        self.maxLength = maxLength

    def __str__(self):
        buf = "\n%s{" % (self.name)
        buf += "\n    base_type=%s maxlength=%s" % (self.base, self.maxLength)
        buf += "\n}"
        return buf

class MyAttr:
    def __init__(self, name, atype, ause):
        self.name = name
        self.type = atype
        self.use = ause

class MyElement:

    def __init__(self, name, mino, maxo, etype):
        self.name = name
        self.minO = mino
        self.maxO = maxo
        self.type = etype

        self.primitiveFields = dict()
        self.customFields = dict()

    def appendAttr(self, attrName, myattr):
        self.primitiveFields[attrName] = myattr

    def appendElement(self, elName, element):
        self.customFields[elName] = element

    def __str__(self):
        buf = "\n%s{" % (self.name)
        if None != self.type:
            buf += "\n    direct_type=%s" % (self.type)

        for attrName in self.primitiveFields:
            attr = self.primitiveFields[attrName]
            buf += "\n    attr=%s type=%s use=%s" % (attr.name, attr.type, attr.use)

        for eleName in self.customFields:
            ele = self.customFields[eleName]
            buf += "\n    elem=%s min=%s max=%s" % (ele.name, ele.minO, ele.maxO)

        buf += "\n}"

        return buf

class XsdParser2:

    def __init__(self, fp):
        tree = lxml.etree.parse(fp)
        self.elements = dict()
        self.simpleTypes = dict()
        self.typePool = dict()
        self.root = tree.getroot()

    def _buildTab(self, level):
        ret = ""
        for i in range(level):
            ret += "  "
        return ret

    def _buildTagName(self, node):
        name = node.tag
        prefix = "{http://www.w3.org/2001/XMLSchema}"
        if name.startswith(prefix):
            name = name[len(prefix):]
        return name

    def __scanElement(self, node):
        if lxml.etree.Comment == node.tag:
            return
        for child in node:
            if lxml.etree.Comment == child.tag:
                continue
            tagName = self._buildTagName(child)
            if "element" == tagName:
                name = child.get("name")
                if None == name:
                    continue
                else:
                    self.elements[name] = child
            self.__scanElement(child)

    def __extractDirectElements(self, node):
        pending = []
        for child in node.getchildren():
            if lxml.etree.Comment == child.tag:
                continue
            tagName = self._buildTagName(child)

            if "element" == tagName:
                myel = MyElement(child.get("name"),
                                 child.get("minOccurs"),
                                 child.get("maxOccurs"),
                                 child.get("type"))
                pending.append(myel)

        return pending

    def __buildComplexType(self, node, el):
        if None == el:
            return None
        for child in node.getchildren():
            if lxml.etree.Comment == child:
                continue
            tagName = self._buildTagName(child)
            if "attribute" == tagName:
                myattr = MyAttr(child.get("name"), child.get("type"), child.get("use"))
                el.appendAttr(myattr.name, myattr)
            elif "sequence" == tagName:
                eles = self.__extractDirectElements(child)
                for element in eles:
                    el.appendElement(element.name, element)
            else:
                info("unknown node: %s" % (tagName))
        return el


    def __buildSimpleType(self, child, el):
        info("__buildSimpleType()")
        return el


    def printElement(self, el):
        buf = "\n%s{" % (el.name)
        if None != el.type:
            if el.type in self.typePool:
                baseType = self.typePool[el.type]
                buf += "\n    direct_type=base:%s length=%s" % (baseType.base, baseType.maxLength)
            else:
                buf += "\n    direct_type=%s" % (el.type)

        for attrName in el.primitiveFields:
            attr = el.primitiveFields[attrName]
            buf += "\n    attr=%s type=%s use=%s" % (attr.name, attr.type, attr.use)

        for eleName in el.customFields:
            ele = el.customFields[eleName]
            buf += "\n    elem=%s min=%s max=%s" % (ele.name, ele.minO, ele.maxO)

        buf += "\n}"
        print(buf)


    def procElement(self, element):

        name = element.get("name")
        mino = element.get("minOccurs")
        maxo = element.get("maxOccurs")
        etype = element.get("type")

        el = MyElement(name, mino, maxo, etype)

        children = element.getchildren()

        if len(children) > 0:
            for child in children:
                if lxml.etree.Comment == child.tag:
                    continue
                tagName = self._buildTagName(child)

                if "complexType" == tagName:
                    el = self.__buildComplexType(child, el)

                elif "simpleType" == tagName:
                    el = self.__buildSimpleType(child, el)

                else:
                    print("unknown tag: %s" % (tagName))
        # else: info ("element:%s has no child" % (name))

        if None == el:
            print("None element: %s" % (name))
        else:
            self.printElement(el)

    def __scanSimpleType(self, node):
        if lxml.etree.Comment == node.tag:
            return

        for child in node:
            if lxml.etree.Comment == child.tag:
                continue

            tagName = self._buildTagName(child)

            if "simpleType" == tagName:
                name = child.get("name")
                if None == name:
                    continue
                else:
                    self.simpleTypes[name] = child
            self.__scanSimpleType(child)


    def __extractSimpleTypeMaxLength(self, node):
        for child in node:
            if lxml.etree.Comment == child.tag:
                continue
            tagName = self._buildTagName(child)
            if "maxLength" == tagName:
                return int(child.get("value"))
        return "0"


    def __buildMySimpleType(self, node):
        typeName = node.get("name")
        for child in node.getchildren():
            if lxml.etree.Comment == child.tag:
                continue
            tagName = self._buildTagName(child)
            if "restriction" == tagName:
                restNode = child
                baseType = restNode.get("base")
                maxLength = self.__extractSimpleTypeMaxLength(child)
                return MySimpleType(typeName, baseType, maxLength)
            else:
                error("unknown node tag: %s" % (tagName))
        error("failed to build my-simple-type")
        return None

    def proc(self):
        # round 1, scan for element
        self.__scanSimpleType(self.root)
        self.__scanElement(self.root)
        # process all simple-types
        for key in self.simpleTypes:
            node = self.simpleTypes[key]
            mySimpleType = self.__buildMySimpleType(node)
            self.typePool[key] = mySimpleType
        # process all element
        for key in sorted(self.elements.keys()):
            element = self.elements[key]
            self.procElement(element)

def main():
    xp = XsdParser2(sys.argv[1])
    xp.proc()

if __name__ == '__main__':
    exit(main())
