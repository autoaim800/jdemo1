import sys


"""
create table t_urls (
    host varchar(45), 
    hash varchar(45), 
    url text, 
    status int default 0, 
    primary key (host, hash)
);

create table t_shopify_items (
    host varchar(45),
    hash varchar(45), 
    handle varchar(120), 
    item_cat varchar(120), 
    item_type varchar(45), 
    item_title varchar(120),
    item_body text,
    product_code varchar(45),
    vendor varchar(45),
    variant_price float(6,2),
    img_src text,
    primary key(host, hash)
);"""
import sqlite3
from xlwt import Workbook
from xlwt.ExcelFormula import Formula
sql = """select b.url, a.handle, a.item_cat, 
    a.item_type, a.item_title, a.item_body,
    a.product_code, a.vendor, a.variant_price,
    a.img_src, a.host, a.hash
from t_shopify_items a
left join t_urls b on a.hash = b.hash
where a.variant_price is not null
"""
def main():
    dbName = sys.argv[1]
    db = sqlite3.connect(dbName)
    wb = Workbook()
    ws = wb.add_sheet("Sheet1")
    
    rowNum = 0
    for row in db.execute(sql):
        url = row[0]        
        handle = row[1]
        itemCat = row[2]
        
        itemType = row[3]
        itemTitle = row[4]
        itemBody = row[5]
        
        productCode = row[6]
        vendor = row[7]
        priceStr = row[8]
        
        imgSrc = row[9]
        host = row[10]
        urlHash = row[11]
        
        if imgSrc.startswith('/'):
            imgSrc = "http://%s%s" % (host, imgSrc)
        if None == priceStr:
            print("None price %s %s\n %s" % (priceStr, urlHash, url))
            continue
        variantPrice = "%.2f" % (float(priceStr))
        link = """HYPERLINK("%s"; "click")""" % (url)
        clickable = Formula(link)
        
        ws.write(rowNum, 0, handle)
        ws.write(rowNum, 1, itemTitle)
        ws.write(rowNum, 2, itemBody)

        ws.write(rowNum, 3, vendor)        
        ws.write(rowNum, 4, itemType)
        ws.write(rowNum, 5, itemCat)
        
        ws.write(rowNum, 6, productCode)        
        ws.write(rowNum, 21, variantPrice)     
        ws.write(rowNum, 26, imgSrc)                
        
        ws.write(rowNum, 29, url)           
        ws.write(rowNum, 31, clickable)
        
        rowNum += 1
        if rowNum % 100 == 0:
            sys.stdout.write('.')    
        
    wb.save("out.xls")
    db.close()    


if __name__ == '__main__':
    exit(main())