from supabase import create_client, Client
from dotenv import load_dotenv

import os

def conect_toDatabase(url: str|None = None, key: str|None = None) -> Client:
    load_dotenv()
    
    _url: str = os.getenv("SUPABASE_URL") if url == None else url
    _key: str = os.getenv("SUPABASE_KEY") if key == None else key

    supabase: Client = create_client(_url, _key)
    return supabase

def login_toDatabase(supabase: Client, email: str|None = None, password: str|None = None) -> bool:
    if type(supabase) != Client:
        raise TypeError("Supabase need to be of Client type")
    
    load_dotenv()

    _email: str = os.getenv("USER_EMAIL") if email == None else email
    _psw: str = os.getenv("USER_PSW") if password == None else password

    try:
        supabase.auth.sign_in_with_password(
            {
                "email": _email,
                "password": _psw
            }
        )

        return True
    except Exception as e:
        print(f"An error ocurred: {e}")
        return False