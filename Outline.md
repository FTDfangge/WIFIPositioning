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
  Client and Server(network): a6, 2020-07-01, 2d
  User system: a5, 2020-07-01,2d
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
- [ ] 1. Requirement analysis
- [ ] 2. basic UI design
- [ ] 3. UI beautify
- [ ] 4. UI design(coding)
- [ ] 5. Client and Server(network)
- [ ] 6. Position
- [ ] 7. Element recommend
- [ ] 8. Information query
- [ ] 9. Feedback
- [ ] 10. Check Presence
- [ ] 11. MalFunction Remind
- [ ] 12. PPT
- [ ] 13. Document

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
|Requirement analysis|||
||||
|basic UI design|Liujiaxu||
|UI beautify|Liujiaxu||
|UI design(coding)|Liujiaxu||
|Client and Server(network)|Zhangfu||
|Position|||
||||
|Element recommend|||
|Information query|||
|Feedback|||
|Check Presence|||
|MalFunction Remind|||
||||
|PPT|||
|Document|||



----------------------------
