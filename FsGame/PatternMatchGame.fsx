#load "JsonCheckFinal.fsx"
open JSONTest

/// OnlineTA
///
/// How to compile:
/// <code>
/// fsharpc PatternMatchGame.fs
/// </code>
///
/// Author: Error404
/// Date: 2016/05/15

let rnd = System.Random()

let foo minNum maxNum = 
    //foo 1 10, means numbers from 1 to 10
    let first = rnd.Next(minNum, maxNum+1)
    first

let hint = ", \"hint\": \"Write the answer without quotationmarks\""

/// <summary>Generates a question based on the game type</summary>
/// <returns>String</returns>
let getQuestion (x) = 
  match x with
  | 2 ->  let y = foo 0 6
          let z = foo 0 6
          let randIntArray = [|foo 0 6;foo 0 6;foo 0 6;foo 0 6|]
          let str = match (y,z) with
                    | (y,z) when y = randIntArray.[0] || z = randIntArray.[1] -> "Hello"
                    | (y,z) when y <= randIntArray.[2] && z >= randIntArray.[3] -> "World"
                    | (_,_) -> "!"
          ("{ \"question\": \"let y = ") + string(y) + ("\\nlet z = ") + string(z) 
          + ("\\nmatch (y,z) with\\n| (y,z) when y = ") + string(randIntArray.[0]) + (" || z = ") + string(randIntArray.[1]) 
          + (" -> ''Hello''\\n| (y,z) when y <= ") + string(randIntArray.[2]) + (" && z >= ") + string(randIntArray.[3]) 
          + (" -> ''World''\\n| (_,_) -> ''!''\", \"answer\": \"") + str + ("\"") + hint + (" }")
  //| 3 ->  //Make Pattern where user puts in missing thing.
  //| 4 -> 
  | _ ->  let y = foo 0 3
          let str = match y with | 1 -> "Hello" | 2 -> "World" | _ -> "!"
          ("{ \"question\": \"let y = ") + string(y) 
          + ("\\nmatch y with\\n| 1 -> ''Hello''\\n| 2 -> ''World''\\n| _ -> ''!''\", \"answer\": \"") + str + ("\"") + hint + (" }")

/// <summary>Evaluates answer versus calculated answer, by calcutating the lemma</summary>
/// <returns>String</returns>
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