import os
import random
from tkinter import CURRENT
from typing import List, Tuple

def generate_epc() -> str :
    CHAR_LIST = "0123456789ABCDEF"

    epc: str = ""

    for _ in range(24):
        random_index = random.randint(0, len(CHAR_LIST))
        epc += CHAR_LIST[random_index - 1]

    return epc



def generate_purchased_state() -> bool :

    random_number = random.randint(0, 100)

    if random_number > 70:
        return False
    else:
        return True



CURRENT_DIR: str = os.path.dirname(os.path.abspath(__file__))

epc_list: List[Tuple[str, bool]] = []

for i in range(50):
    
    epc: str = generate_epc()
    while (epc in epc_list):
        epc = generate_epc()

    is_purchased: bool = generate_purchased_state()

    epc_list.append([epc, is_purchased])

file = open(CURRENT_DIR + "/seed.csv", "wt")

file.write("rfid, isPurchased\n")

for item in epc_list:
    file.write("{}, {}\n".format(item[0], str(item[1]).lower()))

file.close()