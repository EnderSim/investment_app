def save(user_id):
    with open("saved.txt","w") as file:
        file.write(user_id)
