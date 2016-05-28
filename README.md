# PiCalculusJava

This project is an implementation of Pi-Calculus in Java and it was a part of my thesis at University of Cyprus - Department of Computer Science.
The user has to describe the modelled pi-calculus system in a specific XML syntax:
P | Q:   
<parP> 
<process>P</process> 
<process>Q</process> 
</parP> 

x(y:T)P∶
<in> 
<subj>x</subj> 
<obj>y</obj> 
<type>T</type> 
<process>P</process> 
</in> 

x<z>P∶ <out> 
<subj>x</subj> 
<obj>z</obj> 
<process>P</process> 
</out> 


!P : <repl>! 
< process>P</process>
</repl> 

0:<Nil>0</Nil> 

(v G)P: 
<resGP> 
<group>G</group> 
<process>P</process> 
</resGP> 

(v G)S: 
<resGS> 
<group>G</group> 
<system>S</system> 
</resGS> 

(v a:T)S: <resNS> 
<name>a</name> 
<type>T</type> 
<system>S</system> 
</resNS> 

S1 | S2 : 
<parS> 
<system>S1</system> 
<system>S2</system> 
</parS>


