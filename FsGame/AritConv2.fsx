// return = * + 1 2 - 3 4
module AritGame
//////////////////////
let a = System.Random()

type Difficulty (Diff:int) = class
  let mutable StringCollector = ""
  
  member x.Pseudo() = a.Next(1, 100).ToString()
  
  member x.Numeric() =
    let b = a.Next(1, 3)
    let c = a.Next(1, 5)
    if Diff= 2 || Diff < 2 then
      match b with
        | 1 -> " + "
        | 2 -> " - "
        | _ -> failwith "OOB"
      else
        match c with
          | 1 -> " + "
          | 2 -> " - "
          | 3 -> " * "
          | 4 -> " / "
          | _ -> failwith "OOB"

  member x.AritString calcLength =
    for i in 1..calcLength do
      StringCollector <- StringCollector + x.Pseudo() + x.Numeric()
    StringCollector <- StringCollector + x.Pseudo()
    StringCollector
end

/// Random indicator for whether or not parentesis should be used, different pattern matching if true, and if not -----------------------------------------------------------
let calcHandler x =

  let o = new Difficulty(x)
  let mutable outString =
    match x with
      | x when ((x >= 1) && (x <= 5)) -> o.AritString x
      | _ -> failwith "Index out of bounds"
  
  System.Console.WriteLine("{ \"question\": \"" + outString + "\" }")
  outString
  //printfn "\n %s \n \n" outString
  //printfn "%A" outString
  //printfn "%A" outString

//////////////////////

//let ArrayList = stringInput.Split [|' '|] 

let mega(list) =
  let outString = "( " + list + " )"
  let list2 = outString.Split [|' '|]

  let mutable Collector = list2
  let mutable i = 0
  let mutable firstIn = 0
  let mutable lastIn = 0
  let mutable tmp = ""
  let mutable StartColl = [|""|]
  let mutable EndColl = [|""|]
  let mutable lI = 0
  let mutable fI = 0
  let mutable Clicker = [||]
  
  while i < (Array.length Collector) do
    //printfn "%A" i
    //printfn "%A" (Array.length list)
    //printfn "%A" (Array.length Collector)
    //printfn "Coll : %A" Collector


    if Collector.[i] = "(" then
      firstIn <- i
      Collector.[i] <- "rm"
      fI <- 1
      
    if Collector.[i] = ")" then
      lastIn <- i
      Collector.[i] <- "rm2"
      lI <- 1
      
    if (fI = lI) && firstIn < lastIn then
      let mutable index = firstIn
      while index < lastIn do
        match Collector.[index] with
          | "+" -> tmp <- tmp + " +"
                   //Collector <- Collector |> Array.filter ((<>)"+")
                   Collector.[index] <- ""
          | "*" -> tmp <- tmp + " *"
                   //Collector <- Collector |> Array.filter ((<>)"*")
                   Collector.[index] <- ""
                   
          | "-" -> tmp <- tmp + " -"
                   //Collector <- Collector |> Array.filter ((<>)"-")
                   Collector.[index] <- ""

          | "/" -> tmp <- tmp + " /"
                   //Collector <- Collector |> Array.filter ((<>)"/")
                   Collector.[index] <- ""

          | _ -> ()
      
        index <- index + 1

      StartColl <- Collector.[0..firstIn]
      //printfn "%A" StartColl
      EndColl <- Collector.[firstIn+1..(Collector.Length-1)]
      //printfn "%A" EndColl
      //printfn "temp %A" tmp
      Collector <- Array.append StartColl [|tmp|]
      //printfn "Beta: %A" Collector
      Collector <- Array.append Collector EndColl
      Collector <- Collector |> Array.filter ((<>)"")
      //printfn "Prime: %A" Collector
      //printfn "First Index : %A" firstIn
      //printfn "Last Index : %A" lastIn

      
      firstIn <- 0
      lastIn <- 0

      fI <- 0
      lI <- 0
      
      i <- i + 1

    //Count and print
    i <- i + 1

  //printfn "%A" tmp
  i <- 0
  
  while i < (Array.length Collector) do

    if Collector.[i] = "rm2" then
      firstIn <- i
      fI <- 1
      
    if Collector.[i] = "rm" then
      lastIn <- i
      lI <- 1
      
    if (fI = lI) && (firstIn = (lastIn - 2)) then
      let mutable index = firstIn
      while index < lastIn do
        match Collector.[index] with
          | "+" -> tmp <- tmp + " +"
                   //Collector <- Collector |> Array.filter ((<>)"+")
                   Collector.[index] <- ""
          | "*" -> tmp <- tmp + " *"
                   //Collector <- Collector |> Array.filter ((<>)"*")
                   Collector.[index] <- ""
                   
          | "-" -> tmp <- tmp + " -"
                   //Collector <- Collector |> Array.filter ((<>)"-")
                   Collector.[index] <- ""

          | "/" -> tmp <- tmp + " /"
                   //Collector <- Collector |> Array.filter ((<>)"/")
                   Collector.[index] <- ""

          | _ -> ()
      
        index <- index + 1


      Collector <- Array.append [|tmp|] Collector
      firstIn <- 0
      lastIn <- 0

      fI <- 0
      lI <- 0
    i <- i + 1
  
  //printfn "end"
  //printfn "%s" (System.String.Join(" ", Collector))
  //printfn "%A %A" StartColl EndColl
  let final = Collector
              |> Array.filter ((<>)"rm")
              |> Array.filter ((<>)"rm2")
              |> Array.filter ((<>)"")

  let endRes = System.String.Join(" ", final)

  endRes
//return megalist and filter rm (remove) from it

//let final = mega(ArrayList)

//let fin' = final
//           |> Array.filter ((<>)"rm")
//           |> Array.filter ((<>)"rm2")
//           |> Array.filter ((<>)"")

//let endResult = System.String.Join(" ", fin')
           

//printfn "%A" (endResult)


////////////////////

let getQuestion (x) = (calcHandler x)


let evalAnswer (question, answer) = 
  if mega(question) = answer then
    System.Console.WriteLine(true)
  else
    System.Console.WriteLine(false)
  
//Takes command line arguments and calls given function
[<EntryPoint>]
let main(args) =
  if args.Length < 1 then
    System.Console.Write("You need to give a function as an argument: getQuestion(x) or evalAnswer(question,answer)")
    -1
  else
    let funcType = args.[0]
    if funcType = "getQuestion" then 
      let input1 = args.[1]
      getQuestion(int(input1))
      ()
    elif funcType = "evalAnswer" then
      if args.Length <> 3 then
        System.Console.Write("evalAnswer needs the following arguments: [question] [answer]")
      else
        let input1 = args.[1]
        let input2 = args.[2]
        evalAnswer(input1, input2)
    0