'''
Created on 19/11/2013

@author: bill
'''
import sys
import os


class ListTempFile:
    
    def __init__(self, wdir):
        self.wdir = wdir
        self.pending = []

    
    def list(self):
        for parentDir, dirNames, fileNames in os.walk(self.wdir):
            for name in fileNames:
                if name.startswith('.'):
                    continue
                firstName, extName = os.path.splitext(name)
                if extName.endswith('~'):
                    fp = os.path.join(parentDir, name)
                    self.pending.append(fp)
        return self.pending

def main():
    if len(sys.argv) > 1:
        wdir = sys.argv[1]
    else:
        wdir = '.'
    lt = ListTempFile(wdir)
    for name in lt.list():
        print(name)

if __name__ == '__main__':
    exit(main())