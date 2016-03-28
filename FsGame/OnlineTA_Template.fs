module TemplateTest
open System

/// OnlineTA
///
/// How to compile:
/// <code>
/// fsharpc OnlineTA_Template.fs
/// </code>
///
/// Author: Error404
/// Date: 2016/03/28


/// <summary>Generates a question based on the game type</summary>
/// <returns>String</returns>
let getQuestion (x) = 
  /// This function must print a JSON string to the console
  /// with the parameter 'question'.
  /// The parameter 'answer' is an optional parameter.
  /// Below is an example of string with both parameters:
  /// \ is used to escape the quotation marks.
  match x with
  | 1 -> Console.WriteLine("{ \"question\": \"string1\", \"answer\": \"string2\" }")
         /// The following is for unit-testing only
         /// "{ \"question\": \"string1\", \"answer\": \"string2\" }"
         /// unit-testing code ends here
  | _ -> Console.WriteLine("")
         /// The following is for unit-testing only
         /// ""
         /// unit-testing code ends here

/// <summary>Evaluates answer versus calculated answer, by calcutating the lemma</summary>
/// <returns>String</returns>
let evalAnswer (question, answer) = 
  ///Code to evaluate the answer based on the question goes here.
  ///This function must return a string containing a bool value.
  let mutable boolVal = true
  if question = answer then boolVal <- true else boolVal <- false
  Console.WriteLine(string(boolVal))
  /// The following is for unit-testing only
  /// string(boolVal)
  /// unit-testing code ends here

//Takes command line arguments and calls given function
[<EntryPoint>]
let main(args) =
  if args.Length < 1 then
    Console.Write("You need to give a function as an argument: getQuestion(x) or evalAnswer(question,answer)")
    -1
  else
    let funcType = args.[0]
    if funcType = "getQuestion" then
      if args.Length <> 2 then
        Console.Write("getQuestion needs the following argument: [difficulty level]")
      else
        let input1 = args.[1]
        match input1 |> Int32.TryParse with
        | true, input1 -> getQuestion(input1)
        | false, _ -> Console.WriteLine("This argument should be an integer")
    elif funcType = "evalAnswer" then
      if args.Length <> 3 then
        Console.Write("evalAnswer needs the following arguments: [question] [answer]")
      else
        let input1 = args.[1]
        let input2 = args.[2]
        evalAnswer(input1, input2)
    0