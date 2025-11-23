from supabase import Client
from tkinter import ttk

import gui.widget as wg
import gui.frame as fr
import utils.database as db
import utils.crud as op
import tkinter as tk

client: Client = db.conect_toDatabase()

if db.login_toDatabase(client):
    print("Login success.")
else:
    print("Failed to login.")

lst_editora = op.read(client, "editora")
lst_escritor = op.read(client, "escritor")
lst_tipo = op.read(client, "tipo")

# ----- TESTE -----
root = wg.creat_window("Bookshelf", "1200x500")

fr.frame_obra(
                root, 
                [entry['nome'] for entry in lst_tipo.data],
                [entry['nome'] for entry in lst_editora.data],
                [entry['nome'] for entry in lst_escritor.data]
              )

wg.creat_menubar(root)

root.mainloop()