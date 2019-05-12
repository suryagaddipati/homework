* Run Cli  like this 
   ` sbt "runMain homework.Cli /tmp/files.txt  |  1" `
   where
     1. arg1 : full path to file
     2. arg2 : seperator 
     3. arg 3: view option (1|2|3)
 
 * Run webapp like this
     ```
      $ sbt
      > jetty:start
     ```
   post data 
    ```
    curl -d 'record=Meow | Chairman | M | Red | 12/26/1893&seperator= | ' -X POST http://localhost:8080/records
    ```
    
