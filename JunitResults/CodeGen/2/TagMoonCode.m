           entry
           addi r14,r0,topaddr
           % processing:  := 
           lw r1,(r0)
           sw (r0),r1
           % processing:  := 
           lw r1,(r0)
           sw (r0),r1
           % processing:  := 
           lw r1,(r0)
           sw (r0),r1
           % processing:  := 
           lw r1,(r0)
           sw (r0),r1
           % processing:  := 
           lw r1,(r0)
           sw (r0),r1
           % processing:  := 
           lw r1,(r0)
           sw (r0),r1
           % processing:  := 
           lw r1,(r0)
           sw (r0),r1
           % processing:  := 
           lw r1,(r0)
           sw (r0),r1
           hlt
 
 a          res 4
b          res 4
c          res 4
d          res 4
e          res 4
f          res 4
g          res 4
h          res 4
exp        res 4
           % buffer space used for console output
buf        res 20
