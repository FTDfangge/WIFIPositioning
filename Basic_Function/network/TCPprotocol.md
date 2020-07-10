
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
|client->server|"M2" + # + "new name" + # + "new personality" + # + "new number"|
|||
|server->client|"YM"|
|server->client|"NM" + # + "Something error"|

----------------------------

## Position Query

### Basic information
|direction|Message type|
|-|-|
|client->server|"Q" + # + "room id"|
|||
|server->client|"YQ" + # + "No feedback"|
|server->client|"YQ" + # + "name" + # + "feedback"|
|server->client|"NQ" + # + "Query failed"|

### Add feedback
|direction|Message type|
|-|-|
|client->server|"F" + # + "room id" + # + "feedback"|
|||
|server->client|"YF"|
|server->client|"NF" + # + "Something error"|

### Malfunction remind
|direction|Message type|
|-|-|
|client->server|"E" + # + "room id" + # + "explain"|

