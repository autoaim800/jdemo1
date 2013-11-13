import sys
import os


class DdlToSql:
    
    def __init__(self):
        self.fields = dict()
        
    def feed(self, lines):
        firstLine = lines[0]
        subs = firstLine.split()
        if len(subs) >= 3:
            subs[2] = subs[2].strip()
            if subs[2].endswith('('):
                subs[2] = subs[2][:-1]
            self.tabltName = subs[2]
        
        for line in lines[1:]:
            subs = line.strip().split(' ', 1)            
            colName = subs[0].strip()
            colAttr = subs[1].strip()
            if colAttr.endswith(','):
                colAttr = colAttr[:-1]
            self.fields[colName] = colAttr         

    
    def cap1word(self, name):
        ch = name[0].upper()
        return "%s%s" % (ch, name[1:])
    
    def toJava(self):
        tpl = """
        String cmd = "insert into """  +self.tabltName + """ (%s) values (%s)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(cmd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        int afrc = 0;
        
        for(obj:){
            try{
            
            %s
            
            afrc += pstmt.executeUpdate();
            
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        
        
        return afrc;
        """
        cols = ""
        binds = ""
        vals = ""
        count = 1
        for name in self.fields:
            fieldType = self.fields[name]
            cols += "%s, " % (name)                        
            vals += "?, "           
            
            if fieldType.startswith("integer"):
                binds += "nullOrInt(pstmt, %s, obj.get%s());\n" % (count, self.cap1word(name))
            elif fieldType.startswith("bit"):
                binds += "nullOrBoolean(pstmt, %s, obj.get%s());\n" % (count, self.cap1word(name))
            elif fieldType.startswith("date"):
                binds += "nullOrDate(pstmt, %s, obj.get%s());\n" % (count, self.cap1word(name))
            elif fieldType.startswith("decimal"):
                binds += "nullOrFloat(pstmt, %s, obj.get%s());\n" % (count, self.cap1word(name))
            elif fieldType.startswith("text") or fieldType.startswith("varchar("):                
                binds += "nullOrString(pstmt, %s, obj.get%s());\n" % (count, self.cap1word(name))
            else:
                sys.stderr.write("unknown type: %s %s" % (count, fieldType))
            count += 1
            
        if cols.endswith(", "):
            cols = cols[:-2]
        if vals.endswith(", "):
            vals = vals[:-2]
        return tpl % \
            (cols, vals, binds)
    
    

def main():
    ds = DdlToSql()
    ds.feed(sys.stdin.readlines())
    print(ds.toJava())

if __name__ == '__main__':
    exit(main())