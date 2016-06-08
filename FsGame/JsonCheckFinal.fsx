module JSONTest
open System

///Simple unit test funktion with newline
let testfn (x) (boolVal:bool) = 
  if x then
    printfn "   SUCCESS: evalAnswer returned the correct boolean | evalAnswer returned %b" boolVal
  else
    printfn "   FAILED:  evalAnswer returned the wrong boolean | evalAnswer returned %b" boolVal

/// <summary>Check if a given string x contains the string y</summary>
/// <returns>Bool</returns>
let stringContains (x:string, y:string) =
  match x with
  | x when x.Contains(y) -> true
  | x -> false

/// <summary>Checks the number of accurances of string y in the string x</summary>
/// <returns>Int</returns>
let countSubstring (x:string) (y:string) =
    match y with
    | "" -> 0 // just a definition; infinity is not an int
    | _ -> ((x.Length - x.Replace(y, @"").Length) / y.Length)
//"

/// <summary>Checks if all indexées of A are equal to x</summary>
/// <returns>Bool</returns>
let checkTrue (A:int array) (x:int) =
  let mutable tempBool = true
  for i in 0..A.Length-1 do
    if A.[i] = x then
      tempBool <- false
  tempBool

let mustContain = [|"{";"}";"\"question\":";"\"hint\":";","|]
let optionalContain = [|"\"choices\":";"[";"]";"\"answer\":"|]

/// <summary>Test if a given string has the correct formating</summary>
/// <returns>Unit</returns>
let testIterationQuestion (func, nbFunc) =
  let contFailedArrayPrimary = [|0;0;0;0;0;0|]
  let contFailedArrayOptional = [|1;1;1|]
  let mutable optionalParam = ""
  
  ///Runs through all but the last mustContain
  for i in 0..mustContain.Length-2 do
    if not(stringContains(func, mustContain.[i])) then
      contFailedArrayPrimary.[i] <- 1
  if func.[func.Length - 1] <> '}' then
    contFailedArrayPrimary.[1] <- 1
  if func.[0] <> '{' then
    contFailedArrayPrimary.[0] <- 1
    
  match func with
  ///If string does not contain ","
  | func when ((stringContains(func, ",")) = false) -> 
    for k in 0..contFailedArrayPrimary.Length-1 do
      contFailedArrayPrimary.[k] <- 1
  ///If string contains both "\"choices\":" and "\"answer\":"
  | func when (stringContains(func, optionalContain.[3])) && (stringContains(func, optionalContain.[0])) -> 
    if ((countSubstring func "\"") = (16 + (((countSubstring func ",") - 3) * 2))) then
      contFailedArrayOptional.[0] <- 0
    if stringContains(func, "[") then
      contFailedArrayOptional.[1] <- 0
    if stringContains(func, "]") then
      contFailedArrayOptional.[2] <- 0
    if (countSubstring func ",") < 3 then
      contFailedArrayPrimary.[5] <- 1
  ///If string contains "\"choices\":"
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
  ///If string contains "\"answer\":"
  | func when (stringContains(func, optionalContain.[3])) -> 
    contFailedArrayOptional.[1] <- 0
    contFailedArrayOptional.[2] <- 0
    if ((countSubstring func "\"") = 12) then
      contFailedArrayOptional.[0] <- 0
    if (countSubstring func ",") < 2 then
      contFailedArrayPrimary.[5] <- 1
    optionalParam <- optionalContain.[0]
  ///If string contains neither "\"choices\":" or "\"answer\":"
  | _ ->  for k in 0..contFailedArrayOptional.Length-1 do
            contFailedArrayOptional.[k] <- 0
          if not ((countSubstring func "\"") = 8) then
            contFailedArrayOptional.[0] <- 1
          if (countSubstring func ",") < 1 then
            contFailedArrayPrimary.[5] <- 1
          optionalParam <- optionalContain.[0] + " and " + optionalContain.[3]
  ///Prints out all the essential JSON that is incorrect. If no incorrect JSON, prints Success
  if checkTrue contFailedArrayPrimary 1 then
    printfn "   SUCCESS: Function %d contains all essential JSON" nbFunc
  else
    for k in 0..contFailedArrayPrimary.Length-2 do
      if contFailedArrayPrimary.[k] = 1 then
        printfn "   FAILED:  Function %d does not contain: %s" nbFunc mustContain.[k]
  ///Prints out all the optional JSON that is incorrect. If no incorrect JSON, prints Success
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
