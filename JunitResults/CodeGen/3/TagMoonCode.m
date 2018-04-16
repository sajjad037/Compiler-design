           entry
           addi r14,r0,topaddr
           % processing:  := 
           lw r1,(r0)
           sw (r0),r1
           hlt
 
 fib        res 4
n          res 4
res        res 4
           % buffer space used for console output
buf        res 20
