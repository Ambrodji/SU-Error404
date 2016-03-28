// return = * + 1 2 - 3 4


let TestInput = "( 1 + 2 ) * ( 3 - 4 )"

printfn "%A" (TestInput.Split [|' '|])

let test = TestInput.Split [|' '|]
let ArrayList = test 

let mega(list) =
  let mutable Collector = list
  let mutable i = 0
  let mutable firstIn = 0
  let mutable lastIn = 0
  let mutable tmp = ""
  let mutable StartColl = [|""|]
  let mutable EndColl = [|""|]
  let mutable lI = 0
  let mutable fI = 0
  let mutable Clicker = [||]
  
  while i < (Array.length list) do
    printfn "%A" i

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
          | "+" -> tmp <- "+"
                   Collector <- Collector |> Array.filter ((<>)"+")
          | "*" -> tmp <- "*"
                   Collector <- Collector |> Array.filter ((<>)"*")
          | "-" -> tmp <- "-"
                   Collector <- Collector |> Array.filter ((<>)"-")
          | "/" -> tmp <- "/"
                   Collector <- Collector |> Array.filter ((<>)"/")
          | _ -> ()
      
        index <- index + 1

      StartColl <- Collector.[0..firstIn]
      printfn "%A" StartColl
      EndColl <- Collector.[firstIn+1..(Collector.Length-1)]
      printfn "%A" EndColl
      printfn "temp %A" tmp
      Collector <- Array.append StartColl [|tmp|]
      printfn "Beta: %A" Collector
      Collector <- Array.append Collector EndColl
      Collector <- Collector |> Array.filter ((<>)"")
      printfn "Prime: %A" Collector
      printfn "First Index : %A" firstIn
      printfn "Last Index : %A" lastIn

      
      firstIn <- 0
      lastIn <- 0

      fI <- 0
      lI <- 0
      
      //i <- i + 1

    //Count and print
    i <- i + 1
    printfn "Coll : %A" Collector

  printfn "%A" tmp
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
          | "+" -> tmp <- "+"
                   Collector <- Collector |> Array.filter ((<>)"+")
          | "*" -> tmp <- "*"
                   Collector <- Collector |> Array.filter ((<>)"*")
          | "-" -> tmp <- "-"
                   Collector <- Collector |> Array.filter ((<>)"-")
          | "/" -> tmp <- "/"
                   Collector <- Collector |> Array.filter ((<>)"/")
          | _ -> ()
      
        index <- index + 1


      Collector <- Array.append [|tmp|] Collector
      firstIn <- 0
      lastIn <- 0

      fI <- 0
      lI <- 0
    i <- i + 1
  
  printfn "end"
  //printfn "%A %A" StartColl EndColl
  Collector

//return megalist and filter rm (remove) from it
let final = mega(ArrayList)

let fin' = final
           |> Array.filter ((<>)"rm")
           |> Array.filter ((<>)"rm2")
           |> Array.filter ((<>)"")

let endResult = System.String.Join(" ", fin')
           

printfn "%A" (endResult)


////////////////////
