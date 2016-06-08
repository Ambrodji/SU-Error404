#load "JsonCheckFinal.fsx"
open JSONTest

/// OnlineTA
///
/// How to compile:
/// <code>
/// fsharpc Template.fsx JsonCheckFinal.fsx
/// </code>
///
/// Author: Error404
/// Date: 2016/03/28


/// <summary>Generates a question based on the game type</summary>
/// <returns>String</returns>
let getQuestion (x) = 
  /// This function must print a JSON string to the console
  /// with the parameter 'question' and 'hint'.
  /// The parameter 'answer' and 'options' are an optional parameters.
  /// Option parameter takes a list as an argument: [Option1, Option2, Option3, ...]
  /// The order in wich the JSON is put, does not matter.
  /// \ is used to escape the quotation marks.
  /// Below is an example of string with both parameters:
  match x with
  | 2 -> "{ \"question\": \"string1\", \"answer\": \"string2\", \"hint\": \"string3\" }"
  | 3 -> "{ \"question\": \"string1\", \"hint\": \"string3\", \"choices\": [\"True\", \"False\"] }"
  | 4 -> "{ \"question\": \"string1\", \"answer\": \"string2\", \"hint\": \"string3\", \"choices\": [\"True\", \"False\", \"Cool\"] }"
  ///Difficulty 1 should be the wildcard for less hassle.
  | _ -> "{ \"question\": \"string1\", \"hint\": \"string3\" }"

/// <summary>Evaluates answer versus calculated answer, by calcutating the lemma</summary>
/// <returns>Bool</returns>
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