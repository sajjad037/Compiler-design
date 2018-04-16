           entry
           addi r14,r0,topaddr
           % processing:  := 
           lw r1,(r0)
           sw (r0),r1
           hlt
 
 a          res 4
sum        res 4
           % buffer space used for console output
buf        res 20
