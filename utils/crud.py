from supabase import Client

def insert(client: Client, table: str, data: dict) -> bool|dict:
    if type(client) != Client: raise TypeError("Client need to be of Client type.")
    if type(table) != str: raise TypeError("Table name need to be string.")
    if type(data) != dict: raise TypeError("Data need to be a dictionary.")

    try:
        response = (
            client.table(table)
            .insert(data)
            .execute()
        )

        return response
    except Exception as e:
        print(f"An error ocurred: {e}")
        return False
    
def read(client: Client, table: str) -> bool|dict:
    if type(client) != Client: raise TypeError("Client need to be of Client type.")
    if type(table) != str: raise TypeError("Table name need to be string.")

    try:
        response = (
            client.table(table)
            .select("*")
            .execute()
        )

        return response
    except Exception as e:
        print(f"An error ocurred: {e}")
        return False
    
def update(client: Client, table: str, data: dict, id: int) -> bool|dict:
    if type(client) != Client: raise TypeError("Client need to be of Client type.")
    if type(table) != str: raise TypeError("Table name need to be string.")
    if type(data) != dict: raise TypeError("Data need to be a dictionary.")
    if type(id) != int:  raise TypeError("Id need to be a dictionary.")

    try:
        response = (
            client.table(table)
            .update(data)
            .eq("id", id)
            .execute()
        )

        return response
    except Exception as e:
        print(f"An error ocurred: {e}")
        return False
    
def delete(client: Client, table: str, id: int) -> bool|dict:
    if type(client) != Client: raise TypeError("Client need to be of Client type.")
    if type(table) != str: raise TypeError("Table name need to be string.")
    if type(id) != int:  raise TypeError("Id need to be a dictionary.")

    try:
        response = (
            client.table(table)
            .delete()
            .eq("id", id)
            .execute()
        )

        return response
    except Exception as e:
        print(f"An error ocurred: {e}")
        return False