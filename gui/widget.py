from tkinter import ttk

import tkinter as tk

def creat_window(title: str, size: str) -> tk.Tk:
    window = tk.Tk()
    window.title(title)
    window.geometry(size)

    return window

def creat_menubar(window: tk.Tk):
    menubar = tk.Menu(window)

    # --- Cria os menus ---
    _view = tk.Menu(menubar, tearoff=0)
    _app = tk.Menu(menubar, tearoff=0)

    # --- Adiciona os comandos ---
    _view.add_command(label = "Editora", command = None)
    _view.add_command(label = "Escritor", command = None)
    _view.add_command(label = "Obra", command = None)
    _view.add_command(label = "Genero", command = None)
    _view.add_command(label = "Coleção", command = None)
    _view.add_command(label = "Artista", command = None)
    _view.add_command(label = "Tradutor", command = None)

    _app.add_command(label = "Sobre", command = None)
    _app.add_command(label = "Sair", command = None)

    # --- Adiciona o menu a menubar ---
    menubar.add_cascade(label = "View(s)", menu = _view)
    menubar.add_cascade(label = "App", menu = _app)

    window.config(menu=menubar)