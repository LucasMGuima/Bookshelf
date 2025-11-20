from supabase import Client

import utils.database as db

client: Client = db.conect_toDatabase()

if db.login_toDatabase(client):
    print("Login success.")
else:
    print("Failed to login.")