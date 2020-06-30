# Outline of School WiFi Positioning
## Structure of the project
```graphviz
digraph hierarchy {
  nodesep=1.0 // Increases the separation between nodes

  node [color=Red,fontname=Courier,shape=box] // All nodes will this shape and colour
  edge [color=Purple, style=dashed] // All the lines look like this

  School_WiFi_Positioning->{Basic_Functions Extra_Part}
  Basic_Functions->{User_System Positioning Service_Based_On_Positioning}
  Extra_Part->{}
  
}
```

## flow of the task

```mermaid
gantt
  title Gantt Diagram

  section R-A
  Requirements Analysis: a1, 2020-06-29, 1d
  
  section D-D
  basic UI design: a2, 2020-06-29,1d
  UI beautify: a3, 2020-07-08,2d
  
  section B-F
  
  UI design(coding): a4, 2020-06-30, 3d
  Client and Server(network): a5, 2020-07-01, 2d
  DataBase: a5.1, 2020-07-01, 2d
  User system: a5.2, 2020-07-01,2d
  Position:a6, 2020-07-03,4d
  
  
  
  section E-P
  Element recommend: a8, 2020-07-08, 3d
  Information query: a9, 2020-07-08, 2d
  Feedback: a10, 2020-07-08, 2d
  Check Presence: a11, 2020-07-08, 2d
  MalFunction Remind: a12, 2020-07-08, 3d
  
  section END
  Finish the Document: a13, 2020-07-11,1d
  PPT: a14, 2020-07-11,1d
 
```
- [x] 1. Requirement analysis
- [ ] 2. basic UI design
- [ ] 3. UI beautify
- [ ] 4. UI design(coding)
- [ ] 5. Client and Server(network)
- [ ] 6. DataBase
- [ ] 7. Position
- [ ] 8. Element recommend
- [ ] 9. Information query
- [ ] 10. Feedback
- [ ] 11. Check Presence
- [ ] 12. MalFunction Remind
- [ ] 13. PPT
- [ ] 14. Document

|In short|Meaning|
|-|-|
|R-A|Requirements Analysis|
|D-D|Demo Design|
|B-F|Basic Function|
|E-P|Extra Part|
|END|end of the task(including the file and ppt)|

----------------------------

## basic functions
1. UI design
2. User system
3. Position
4. Network

## extra functions
1. Element recommend
2. Location information query
3. Recent feedbacks and all feedbacks
4. Malfunction remind
5. Check presenece in class

----------------------------

## distribute
|Work|Manager|Task Explain|
|-|-|-|
|Requirement analysis|LiuJiaXu||
||||
|Basic UI design|Liujiaxu|Using modao|
|UI beautify|Liujiaxu|using modao|
|UI design(coding)|Liujiaxu||
|Client and Server(network)|Zhangfu||
|Position|ZQK||
||||
|Element recommend|huanglin||
|Information query|helanlan||
|Feedback|Zhangfu||
|Check Presence|ZQK||
|MalFunction Remind|Hemaoxin||
||||
|PPT|huanglin||
|Document|ZQK||

----------------------------
