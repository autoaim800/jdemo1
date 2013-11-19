'''
deprecated
'''

import sys
import os
import lxml.etree

class XsdParser:

    def buildWhitespace(self, level):
        ret = ""
        for i in range(level):
            ret += "  "
        return ret
    
    def buildTagName(self, node):        
        name = node.tag
        prefix = "{http://www.w3.org/2001/XMLSchema}"
        if name.startswith(prefix):
            name = name[len(prefix):]
        return name    
    
    def buildAttrs(self, node):
        pending = ""
        for key in node.keys():
            val = node.get(key)
            pending += "%s=%s " % (key, val)
        return pending.strip()    

    def procComplexSequence(self, node, level):
        # only parse first child of the sequence-node
        for child in node.iterchildren():
            self.procNode(node, level + 1)
            break

    def procComplexType(self, node, level):
        # ignore its attribute
        for child in node.iterchildren():
            if "sequence" == child.tag:
                self.procComplexSequence(child, level + 1)
            else:
                self.procNode(child, level + 1)
    

    def registerElement(self, tagName):
        self.customElements.add(tagName)    

    def procSimpleType(self, node, level):
        pass
    
    def procNode(self, node, level):
        
        if lxml.etree.Comment == node.tag:
            return
        
        leading = self.buildWhitespace(level)
        tagName = self.buildTagName(node)
        
        if "complexType" == tagName:
            #print("%s%s" % (leading, tagName))
            self.procComplexType(node, level + 1)
        elif "simpleType" == tagName:
            print("%s%s" % (leading, tagName))
            #self.procSimpleType(node, level+1)
        else:
            if "element" == tagName:
                self.registerElement(node.get("name"))                
            attrs = self.buildAttrs(node)                  
            print("%s%s %s" % (leading, tagName, attrs))            
            for child in node.iterchildren():
                self.procNode(child, level + 1)

    def __init__(self, fp):
        tree = lxml.etree.parse(fp)
        root = tree.getroot()
        self.customElements = set()
        
        self.procNode(root, 0)
        
        #for name in self.customElements:
            #print(name)

def main():
    fp = sys.argv[1]
    xp = XsdParser(fp)

if __name__ == '__main__':
    exit(main())
