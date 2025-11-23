from tkinter import ttk

import tkinter as tk

_font = ('Arial', 10)

def _clear_window(window: tk.Tk) -> None:
    for widget in window.winfo_children():
        widget.destroy()

def _setUp_grid(window: tk.Tk) -> None:
    window.grid_columnconfigure(0, weight=3) #30%
    window.grid_columnconfigure(1, weight=7) #70%
    window.grid_rowconfigure(1, weight=9) #90%

def frame_obra(window: tk.Tk, lst_tipo: list, lst_editora: list, lst_escritor: list) -> None:
    _clear_window(window)
    _setUp_grid(window)

    frame_novo = tk.Frame(window)

    # --- Coluna Esquerda ---
    ttk.Label(frame_novo, text="Nova Obra", font=_font).grid(column=0, row=0, pady=5)

    ttk.Label(frame_novo, text="Nome:", font=_font).grid(column=0, row=1, pady=5)
    entry_name = tk.Entry(frame_novo, width=20)
    entry_name.grid(column=1, row=1)

    ttk.Label(frame_novo, text="Editora:", font=_font).grid(column=0, row=2, pady=5)
    editora = ttk.Combobox(frame_novo, width=10, textvariable=tk.StringVar())
    editora.grid(column=1, row=2)

    ttk.Label(frame_novo, text="Tipo:", font=_font).grid(column=0, row=3, pady=5)
    tipo = ttk.Combobox(frame_novo, width=10, textvariable=tk.StringVar())
    tipo.grid(column=1, row=3)

    ttk.Label(frame_novo, text="Escritor:", font=_font).grid(column=0, row=4, pady=5)
    escritor = ttk.Combobox(frame_novo, width=15, textvariable=tk.StringVar())
    escritor.grid(column=1, row=4)

    ttk.Label(frame_novo, text="Adquirido:", font=_font).grid(column=0, row=5, pady=5)
    adquirido = ttk.Combobox(frame_novo, width=5, textvariable=tk.StringVar())
    adquirido.grid(column=1, row=5)

    ttk.Button(
            frame_novo, 
            text="Adicionar", 
            command=lambda: print(
                    f"Nome: {entry_name.get()}"
                )
        ).grid(column=1, row=6)

    editora['values'] = lst_editora
    tipo['values'] = lst_tipo
    escritor['values'] = lst_escritor
    adquirido['values'] = ('Sim', 'Não')

    tipo.current(0)
    editora.current(0)
    escritor.current(0)
    adquirido.current(1)

    # --- Coluna Direita ---
    table = ttk.Treeview(window, columns=("c1", "c2", "c3", "c4", "c5"), show='headings', height=10)
    table.grid(column=1, row=0, pady=5)

    table.column('# 1', anchor="center")
    table.heading('# 1', text='Nome')
    table.column('# 2', anchor="center")
    table.heading('# 2', text='Tipo')
    table.column('# 3', anchor="center")
    table.heading('# 3', text='Escritor')
    table.column('# 4', anchor="center")
    table.heading('# 4', text='Editora')
    table.column('# 5', anchor="center")
    table.heading('# 5', text='Adquirido')

    frame_novo.grid(column=0, row=0)