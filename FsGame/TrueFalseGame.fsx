#load "JsonCheckFinal.fsx"
open JSONTest
open System

/// OnlineTA
///
/// How to compile:
/// <code>
/// fsharpc TrueFalseGame.fsx JsonCheckFinal.fsx
/// </code>
///
/// Author: Error404
/// Date: 2016/03/28

let hint = ", \"hint\": \"Answer is always true or false\""
let rnd = System.Random()

/// Return a random bool from minNum to maxNum
let foo minNum maxNum = 
    let first = rnd.Next(minNum, maxNum)
    first

/// Return a random bool value
let booly () =
  let first = rnd.Next(0, 2)
  let mutable boolVal = true 
  if first = 0 then boolVal <- true else boolVal <- false
  boolVal

///Generates a lemma and calculates answer
let getQuestion (x) = 
  match x with 
  |2 -> " "
  |3 -> let array1 = Array.init 10 (fun x -> foo 1 10)
        let array2 = [| (booly ()); (booly ()) |]

        /// Checks if answer is correct
        let answerCheck =
          let mutable count = 0
          for i in 0..(array1.Length-1) do
            if (array1.[i] % (array1.[0])) = 0 then
              count <- count + array1.[i]
          if count < (array1.[1] + array1.[2]) then array2.[0] else array2.[1]

        /// Writes string for user to read
        let mutable arrString = ""
        for i in 0..(array1.Length-2) do
          arrString <- arrString + (string(array1.[i]) + ";")

        "{ \"question\": \"let array1 = [|" + arrString + string(array1.[9]) + "|]\\nlet mutable count = 0\\n\\n" 
                 + "for i in 1..(array1.Length-1) do\\n  if array1.[i] % " 
                 + string(array1.[0]) + " = 0 then\\n    count <- count + array1.[i] \\n  if count < ("
                 + string(array1.[1]) + " + " 
                 + string(array1.[2]) + ") then " + string(array2.[0]) + " else " 
                 + string(array2.[1]) + "\", \"answer\": \"" + string(answerCheck) + "\", \"choices\": [\"True\", \"False\"] }" + hint
  |_ -> let array1 = [| (foo 1 100); (foo 1 100) |]
        let array2 = [| (booly ()); (booly ()) |]
        let answerCheck = if array1.[0] < array1.[1] then
                            array2.[0] else array2.[1]
        
        "{ \"question\": \"if " 
          + string(array1.[0]) + " < " + string(array1.[1]) 
          + " then " + string(array2.[0]) + " else " + string(array2.[1]) 
          + "\", \"answer\": \"" + string(answerCheck) + "\", \"choices\": [\"True\", \"False\"] }" + hint

///Evaluates lemma against given answer versus calculated answer
let evalAnswer (question, answer) = 
  ///Code to evaluate the answer based on the question goes here.
  ///This function must return a string containing a bool value.
  let mutable boolVal = true
  if question <> answer then boolVal <- not boolVal
  boolVal
    

/// <summary>Takes command line arguments and calls the given functions</summary>
/// <returns>Unit</returns>
[<EntryPoint>]
let main(args) =
  if args.Length < 1 then
    printfn("You need to give a function as an argument: getQuestion(x) or evalAnswer(question,answer)")
    -1
  else
    let funcType = args.[0]
    if funcType = "getQuestion" then
      if args.Length <> 2 then
        printfn("getQuestion needs the following argument: [difficulty level]")
      else
        let input1 = args.[1]
        match input1 |> System.Int32.TryParse with
        | true, input1 -> printfn "%s" (getQuestion(input1))
        | false, _ -> printfn ("This argument should be an integer")
    elif funcType = "evalAnswer" then
      if args.Length <> 3 then
        printfn("evalAnswer needs the following arguments: [question] [answer]")
      else
        let input1 = args.[1]
        let input2 = args.[2]
        printfn "%b" (evalAnswer(input1, input2))
    elif funcType = "debug" then
      if args.Length <> 2 then
        printfn("debug needs the following arguments: [number of runs]")
      else
        let input1 = args.[1]
        match input1 |> System.Int32.TryParse with
        | true, input1 -> 
          for i in 1..int(input1) do
            testIterationQuestion (getQuestion(i), (i))  
          printfn "   -----\n"
          testfn (evalAnswer("1", "1") = true) (evalAnswer("1", "1"))
          testfn (evalAnswer("1", "2") = false) (evalAnswer("1", "2"))
        | false, _ -> printfn ("This argument should be an integer")
    0