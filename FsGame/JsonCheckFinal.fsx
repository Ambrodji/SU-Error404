module JSONTest
open System

let testfn x = if x then printfn "." else printfn "#"

let test x = if x then printf "." else printf "#"

let stringContains (x:string, y:string) =
  match x with
  | x when x.Contains(y) -> true
  | x -> false

let countSubstring (x:string) (y:string) =
    match y with
    | "" -> 0 // just a definition; infinity is not an int
    | _ -> ((x.Length - x.Replace(y, @"").Length) / y.Length)
//"

let checkTrue (A:int array) (x:int) =
  let mutable tempBool = true
  for i in 0..A.Length-1 do
    if A.[i] = x then
      tempBool <- false
  tempBool

let mustContain = [|"{";"}";"\"question\":";"\"hint\":";","|]
let optionalContain = [|"\"choices\":";"[";"]";"\"answer\":"|]


//Tests if all essential string is complete
let testIteration (func, nbFunc) =
  let contFailedArrayPrimary = [|0;0;0;0;0;0|]
  let contFailedArrayOptional = [|1;1;1|]
  let mutable optionalParam = ""
  
  for i in 0..mustContain.Length-2 do
    if not(stringContains(func, mustContain.[i])) then
      contFailedArrayPrimary.[i] <- 1
    
  match func with
  | func when ((stringContains(func, ",")) = false) -> 
    for k in 0..contFailedArrayPrimary.Length-1 do
      contFailedArrayPrimary.[k] <- 1

  | func when (stringContains(func, optionalContain.[3])) && (stringContains(func, optionalContain.[0])) -> 
    if ((countSubstring func "\"") = (16 + (((countSubstring func ",") - 3) * 2))) then
      contFailedArrayOptional.[0] <- 0
    if stringContains(func, "[") then
      contFailedArrayOptional.[1] <- 0
    if stringContains(func, "]") then
      contFailedArrayOptional.[2] <- 0
    if (countSubstring func ",") < 3 then
      contFailedArrayPrimary.[5] <- 1

  | func when (stringContains(func, optionalContain.[0])) -> 
    if ((countSubstring func "\"") = (12 + (((countSubstring func ",") - 2) * 2))) then
      contFailedArrayOptional.[0] <- 0
    if stringContains(func, "[") then
      contFailedArrayOptional.[1] <- 0
    if stringContains(func, "]") then
      contFailedArrayOptional.[2] <- 0
    if (countSubstring func ",") < 2 then
      contFailedArrayPrimary.[5] <- 1
    optionalParam <- optionalContain.[3]


  | func when (stringContains(func, optionalContain.[3])) -> 
    contFailedArrayOptional.[1] <- 0
    contFailedArrayOptional.[2] <- 0
    if ((countSubstring func "\"") = 12) then
      contFailedArrayOptional.[0] <- 0
    if (countSubstring func ",") < 2 then
      contFailedArrayPrimary.[5] <- 1
    optionalParam <- optionalContain.[0]

  | _ ->  for k in 0..contFailedArrayOptional.Length-1 do
            contFailedArrayOptional.[k] <- 0
          if not ((countSubstring func "\"") = 8) then
            contFailedArrayOptional.[0] <- 1
          if (countSubstring func ",") < 1 then
            contFailedArrayPrimary.[5] <- 1
          optionalParam <- optionalContain.[0] + " and " + optionalContain.[3]

  if checkTrue contFailedArrayPrimary 1 then
    printfn "   SUCCESS: Function %d contains all essential JSON" nbFunc
  else
    for k in 0..contFailedArrayPrimary.Length-2 do
      if contFailedArrayPrimary.[k] = 1 then
        printfn "   FAILED:  Function %d does not contain: %s" nbFunc mustContain.[k]
      

  if checkTrue contFailedArrayOptional 1 then
    printfn "   SUCCESS: Function %d's optional parameters are correct JSON" nbFunc
  else
    for k in 0..contFailedArrayOptional.Length-1 do
      if k = 0 && contFailedArrayOptional.[0] = 1 then
        printfn "   FAILED:  Function %d has a mismatch between \"quatationmarks\" and the amount of \"options\"." nbFunc
      elif contFailedArrayOptional.[k] = 1 then
        printfn "   FAILED:  Function %d does not contain: %s" nbFunc optionalContain.[k]
  if optionalParam <> "" then
    printfn "   NOTICE:  Optional parameter %s is/are not present" optionalParam
  printfn ""