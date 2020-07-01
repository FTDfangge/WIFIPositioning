
# TCP Protocol

----------------------------

## User information transmission

### Register

|direction|Message type|
|-|-|
|client->server|"R" + # + "name" + # + "password"|
|||
|server->client|"YR" + # + "id"|
|server->client|"NR" + # + "Name already exists"|

### Log in
|direction|Message type|
|-|-|
|client->server|"L" + # + "id" + # + "password"|
|||
|server->client|"YL"|
|server->client|"NL" + # + "user id doesn't exist"|
|server->client|"NL" + # + "password is wrong"|


### Modify information
|direction|Message type|
|-|-|
|client->server|"M" + # + "--name--" + # + "new name"|
|client->server|"M" + # + "--password--" + # + "new password"|
|||
|server->client|"YM"|
|server->client|"NM" + # + "Something error"|

----------------------------

## Position information transmission

### Position yourself

|direction|Message type|
|-|-|
|client->server|----(Not define)|
|||
|server->client|"position X" + # + "position Y"|

### Position information request

|direction|Message type|
|-|-|
|client->server|"RQ" + # + "position X" + # + "position Y"|
|||
|server->client|all information of nearby specify position|

----------------------------
