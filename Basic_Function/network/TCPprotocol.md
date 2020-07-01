
# TCP Protocol

----------------------------

## User information transmission

### Register

|direction|Message type|
|-|-|
|client->server|"name" + space + "password"|
|||
|server->client|"YR" + space + "id"|
|server->client|"NR" + space + "Name already exists"|

### Log in
|direction|Message type|
|-|-|
|client->server|"id" + space + "password"|
|||
|server->client|"YL"|
|server->client|"NL" + space + "user id doesn't exist"|
|server->client|"NL" + space + "password is wrong"|


### Modify information
|direction|Message type|
|-|-|
|client->server|"--name--" + space + "new name"|
|client->server|"--password--" + space + "new password"|
|||
|server->client|"YM"|
|server->client|"NM" + space + "Something error"|

----------------------------

## Position information transmission

### Position yourself

|direction|Message type|
|-|-|
|client->server|----(Not define)|
|||
|server->client|"position X" + space + "position Y"|

### Position information request

|direction|Message type|
|-|-|
|client->server|"RQ" + space + "position X" + space + "position Y"|
|||
|server->client|all information of nearby specify position|

----------------------------
