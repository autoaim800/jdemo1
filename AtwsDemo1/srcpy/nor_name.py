'''
Created on 13/11/2013

@author: bill
'''

import os
import sys

SKIP_EXTS = ["."]
class NorName:
    

    def __norName(self, raw):
        return raw.replace("'", '').replace(',','.').replace(':','.').replace(' - ','.').replace(' ', '.')\
            .replace('\n','.')\
            .replace('..', '.').replace('..', '.').replace('..', '.').replace('..', '.')\
            .replace("Oreilly.", "OReilly.")
    
    def __init__(self, wdir):
        self.pending = []
        for parentDir, dirNames, fileNames in os.walk(wdir):
            for name in fileNames:
                firstName, extName = os.path.splitext(name)
                
                if name.startswith('.') or extName.endswith('~') or extName in SKIP_EXTS:
                    continue
                
                nname = self.__norName(name)
                
                if nname == name:
                    continue
                
                self.pending.append((parentDir, name, nname))

    
    def norminalize(self):
        for dirName, oname, nname in self.pending:            
            nfp = os.path.join(dirName, nname)
            if os.path.exists(nfp):
                continue
            print("%s/\n   %s\n =>%s" % (dirName, oname, nname))
            ofp = os.path.join(dirName, oname)
            os.rename(ofp, nfp)


def main():
    if len(sys.argv) > 1:
        wdir = sys.argv[1]
    else:
        wdir = os.getcwd()
    nn = NorName(wdir)
    nn.norminalize()
    
if __name__ == '__main__':
    exit(main())
