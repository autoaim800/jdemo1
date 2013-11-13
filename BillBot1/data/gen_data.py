dirs = ["EAST", "SOUTH", "WEST", "NORTH"]

def invalid_place_y():
    print ("    Examples: all invalid y-axis")
    print("      | position |")
    tpl = "      | %s,%s,%s |"
    iy = [-9,-8,-7,-6,-5,-4,-3,-2,-1,5,6,7,8,9]
    for d in range(4):
        for x in range(5):
            for y in iy:
                print(tpl % (x, y, dirs[d]))

def invalid_place_x():
    print ("    Examples: all invalid x-axis")
    print("      | position |")
    tpl = "      | %s,%s,%s |"
    ix = [-9,-8,-7,-6,-5,-4,-3,-2,-1,5,6,7,8,9]
    for d in range(4):
        for x in ix:
            for y in range(5):
                print(tpl % (x, y, dirs[d]))

def invalid_place_xy():
    print ("    Examples: all invalid xy-axis")
    print("      | position |")
    tpl = "      | %s,%s,%s |"
    ix = [-9,-8,-7,-6,-5,-4,-3,-2,-1,5,6,7,8,9]
    iy = [-9,-8,-7,-6,-5,-4,-3,-2,-1,5,6,7,8,9]
    for d in range(4):
        for x in ix:
            for y in iy:
                print(tpl % (x, y, dirs[d]))

def invalid_place_f():
    print ("    Examples: all invalid facing")
    print("      | position |")
    tpl = "      | %s,%s,%s |"
    ids = ["UP", "NORTH-WEST", "NORTH-EAST", "DOWN"]
    for d in ids:
        for x in range(5):
            for y in range(5):
                print(tpl % (x, y, d))

def invalid_place_xyf():
    print ("    Examples: all invalid xyfacing")
    print("      | position |")
    tpl = "      | %s,%s,%s |"
    ids = ["UP", "NORTH-WEST", "NORTH-EAST", "DOWN"]
    ix = [-9,-8,-7,-6,-5,-4,-3,-2,-1,5,6,7,8,9]
    iy = [-9,-8,-7,-6,-5,-4,-3,-2,-1,5,6,7,8,9]
    for d in ids:
        for x in ix:
            for y in iy:
                print(tpl % (x, y, d))

def valid_place():
    print ("\n\n\n# valid place")
    tpl = "      | %s,%s,%s |"
    for d in range(4):
        for x in range(5):
            for y in range(5):
                print(tpl % (x, y, dirs[d]))

def valid_move():
    print("\n\n\n# valid move")
    tpl = "      | %s,%s,%s | %s,%s,%s |"
    for d in range(4):
        for x in range(5):
            for y in range(5):
                if d == 0:
                    x2 = x + 1
                    y2 = y
                    if x2 < 5:
                        print(tpl % (x, y, dirs[d], x2, y, dirs[d]))
                elif d == 1:
                    x2 = x
                    y2 = y - 1
                    if y2 >= 0:
                        print(tpl % (x, y, dirs[d], x2, y2, dirs[d]))
                elif d == 2:
                    x2 = x - 1
                    y2 = y
                    if x2 >= 0:
                        print(tpl % (x, y, dirs[d], x2, y2, dirs[d]))
                elif d == 3:
                    x2 = x
                    y2 = y + 1
                    if y2 < 5:
                        print(tpl % (x, y, dirs[d], x2, y2, dirs[d]))

def valid_turn_right():
    print("\n\n\n# valid turn right")
    tpl = "      | %s,%s,%s | %s,%s,%s |"
    for d in range(4):
        for x in range(5):
            for y in range(5):
                d2 = d + 1
                d2 %= 4
                print(tpl % (x, y, dirs[d], x, y, dirs[d2]))

def valid_turn_left():
    print("\n\n\n# valid turn left")
    tpl = "      | %s,%s,%s | %s,%s,%s |"
    for d in range(4):
        for x in range(5):
            for y in range(5):
                d2 = d - 1
                d2 %= 4
                print(tpl % (x, y, dirs[d], x, y, dirs[d2]))

def valid_turn_left2():
    print("\n\n\n# valid turn left2")
    tpl = "      | %s,%s,%s | %s,%s,%s |"
    for d in range(4):
        for x in range(5):
            for y in range(5):
                d2 = d - 2
                d2 %= 4
                print(tpl % (x, y, dirs[d], x, y, dirs[d2]))

def boundary_move():
    print("\n\n\n# valid boundary")
    tpl = "      | %s,%s,%s |"
    for y in range(5):
        print(tpl % (4, y, dirs[0]))
    for x in range(4):
        print(tpl % (x, 0, dirs[1]))
    for y in range(4):
        print(tpl % (0, y, dirs[2]))
    for x in range(4):
        print(tpl % (x, 4, dirs[3]))

if __name__ == "__main__":
    invalid_place_x()
    invalid_place_y()
    invalid_place_xy()
    invalid_place_f()
    invalid_place_xyf()
    #valid_place()
    #valid_move()
    #valid_turn_right()
    #valid_turn_left()
    #valid_turn_left2()
    #boundary_move()