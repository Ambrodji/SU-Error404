#load "OnlineTA_Template.fs"
open TemplateTest
open System

let testfn x = if x then printfn "." else printfn "#"
let test x = if x then printf "." else printf "#"

let stringContains (x:string, y:string) =
  match x with
  | x when x.Contains(y) -> true
  | x -> false

testfn((stringContains(getQuestion(1), "\"question\":") && stringContains(getQuestion(1), "\"answer\":")) = true)
testfn((stringContains(getQuestion(1), "\"blarg1\":") && stringContains(getQuestion(1), "\"blarg2\":")) = false)

testfn((stringContains(getQuestion(-1), "\"question\":") && stringContains(getQuestion(1), "\"answer\":")) = false)
testfn(stringContains(getQuestion(-1), "") = true)

testfn(stringContains(evalAnswer(1,1), "True") = true)
testfn(stringContains(evalAnswer(1,2), "False") = true)