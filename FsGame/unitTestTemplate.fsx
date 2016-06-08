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