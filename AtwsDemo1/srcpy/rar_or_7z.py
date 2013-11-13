'''
Created on 13/11/2013

@author: bill
'''
import sys
import os
import subprocess


class RarOr7z:

    def __init__(self, wdir):
        self.pending = []
        for parentDir, dirNames, fileNames in os.walk(wdir):
            for name in fileNames:
                firstName, extName = os.path.splitext(name)
                if name.startswith('.') or extName.endswith('~'):
                    continue
                if extName in [".pdf", ".chm"]:
                    self.pending.append((parentDir, name))

    def __rarFile(self, dirName, fileName):
        rfp = "%s.rar" % (fileName)
        cmds = ["rar", "a", rfp, fileName]
        rfp = os.path.join(dirName, rfp)
        
        if os.path.exists(rfp):
            return rfp, os.path.getsize(rfp)
        
        retcode = subprocess.check_call(cmds, cwd=dirName)
        if retcode == 0:            
            return rfp, os.path.getsize(rfp)
        
        return None, 0

    def __z7File(self, dirName, fileName):
        zfp = "%s.7z" % (fileName)
        cmds = ["7z", "a", zfp, fileName]
        zfp = os.path.join(dirName, zfp)
        
        if os.path.exists(zfp):
            return zfp, os.path.getsize(zfp)
        
        retcode = subprocess.check_call(cmds, cwd=dirName)
        if retcode == 0:
            return zfp, os.path.getsize(zfp)
        
        return None, 0

    def compress(self):
        for dirName, fileName in self.pending:
            rfp, rsize = self.__rarFile(dirName, fileName)
            zfp, zsize = self.__z7File(dirName, fileName)

            if None == rfp:
                sys.stderr.write("failed to compress %s/%s using rar" % (dirName, fileName))
                continue

            if None == zfp:
                sys.stderr.write("failed to compress %s/%s using 7zip" % (dirName, fileName))
                continue

            sys.stderr.write("%s/%s z:r=%s %s:%s" % (dirName, fileName, abs(zsize - rsize), zsize, rsize))

            if zsize > rsize:
                os.remove(rfp)
            else:
                os.remove(zfp)

def main():
    r7 = RarOr7z(sys.argv[1])
    r7.compress()

if __name__ == '__main__':
    exit(main())
