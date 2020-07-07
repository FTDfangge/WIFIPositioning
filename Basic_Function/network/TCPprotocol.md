
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
|server->client|"YL" + # + "name" + # + "number" + # + "personality"|
|server->client|"NL" + # + "user id doesn't exist"|
|server->client|"NL" + # + "password is wrong"|


### Modify information
|direction|Message type|
|-|-|
|client->server|"M1" + # + "new password"|
|client->server|"M2" + # + "N" + # + "new name" + # + "Y" + # + "new personality" + # + "M" + # + "new number"|
|||
|server->client|"YM"|
|server->client|"NM" + # + "Something error"|

----------------------------
