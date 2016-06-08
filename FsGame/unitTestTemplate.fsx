#load "functionTestLand.fs"
open functionTestLand
open System


//------ Read Console --------

let reporter reportRepository reportSender id =
    let report = reportRepository id
    reportSender report

type report = { id:int; title:string; body:string }

let myReportRepository id =
    { id = id; title = "Great Report"; body = "The report body" }

let myReportSender report =
    printfn "report = %A" report

let myReporter = reporter myReportRepository myReportSender

//------ End Read Console --------

let testfn x = if x then printfn "." else printfn "#"
let test x = if x then printf "." else printf "#"

let stringContains (x:string, y:string) =
  match x with
  | x when x.Contains(y) -> true
  | x -> false

let getLengthOfFunc () =
  let mutable boolVal = true
  let mutable i = 1
  while boolVal do
    getQuestion(i) |> ignore
    if lengthBe <> 0 then
      boolVal <- false
    else
      i <- i + 1
getLengthOfFunc()

let mustContain = [|"{";"}";"\"question\":";"\"hint\":"|]
let canContain = [|"\"choices\":";"[";"]";"\"answer\":"|]
let mutable containsAll = 0
let contFailedArray = [|0;0;0;0|]

let getQuestionContains count =
  for j in 0..mustContain.Length-1 do
    if (stringContains(getQuestion(count), mustContain.[j])) then
      containsAll <- containsAll + 1
    else
      contFailedArray.[j] <- 1
  if containsAll = mustContain.Length then
    printfn "     SUCCESS: Function %d contains all nessesary JSON" count
  else
    printf "     FAILED: Function %d does not contain: " count
    for k in 0..contFailedArray.Length-1 do
      if contFailedArray.[k] = 1 then
        printf "%s ," mustContain.[k]

printfn "getQuestion (x):"
for i in 1..lengthBe do
  getQuestionContains(i)