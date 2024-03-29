package packages

import GUI.stage
import javafx.scene.control.DatePicker
import javafx.scene.layout.BorderPane
import scalafx.application.JFXApp3
import scalafx.scene.Scene
import scalafx.scene.layout.Pane
import scalafx.scene.paint.Color.*
import scalafx.scene.control.{Button, CheckBox, Label, TextField, TextFormatter}
import scalafx.scene.text.Font
import scalafx.scene.control.TextFormatter.Change
import scalafx.scene.layout.StackPane
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.layout.VBox
import scalafx.scene.layout.HBox
import scalafx.scene.layout.GridPane
import scalafx.scene.layout.Background
import scalafx.scene.layout.BackgroundFill
import scalafx.scene.layout.CornerRadii
import scalafx.scene.layout.ColumnConstraints
import scalafx.scene.layout.RowConstraints
import scalafx.scene.paint.Color.*
import scalafx.Includes.*
import javafx.scene.control.{Menu, MenuBar, MenuButton, MenuItem}
//import scalafx.event.ActionEvent
import scalafx.scene.control.Button.sfxButton2jfx
import javafx.event.EventHandler
import javafx.event.ActionEvent
import scala.collection.mutable.ListBuffer

import scala.collection.mutable.Map
import javafx.scene.chart.PieChart
import javafx.scene.chart.PieChart.Data
import javafx.collections.FXCollections

import java.time.*
import javafx.scene.control.DatePicker

import java.util.NoSuchElementException

object GUI extends JFXApp3:

  def start(): Unit =

    stage = new JFXApp3.PrimaryStage:
      title = "Rental Manager"
      width = 950
      height = 600

    val root = GridPane() // Simple pane component
    val root2= GridPane()
    val root3= GridPane()
    val root4= GridPane()
    val root5= GridPane()
    var scene = Scene(parent = root) // Scene acts as a container for the scene graph
    var scene2= Scene(parent = root2)
    var scene3= Scene(parent=root3)
    var scene4= Scene(parent=root4)
    var scene5= Scene(parent=root5)
      stage.setScene(scene) // Assigning the new scene as the current scene for the stage
    val rentalmanager= new RentalManager()
    val nameLabel = Label("Rental Manager") //Create a Label
      nameLabel.textFill = Black //Set text color
      nameLabel.font = Font.font(20) //Text font size. It is also possible to set font family.
      nameLabel.layoutX = 500
      root.children += nameLabel //Add label to GUI.
      val load=  new Button("load")
      load.onAction=(e: ActionEvent) =>
        rentalmanager.loadItem
        rentalmanager.loadrest
        load.setDisable(true)





    val print= new Button("print")


    //item view
      val items= new Button("item view")
        items.onAction = (e: ActionEvent) => stage.setScene(scene2)

      val itemcheck= new Button("Check items")

      val typecheck = new Button("Check item types")

        root2.add(typecheck,200,800)
        typecheck.onAction= (e: ActionEvent) =>
          val box= new VBox()
          root2.add(box,0,1)
          box.children+= new Label(s"item types:")
          println(rentalmanager.itemtypes)

            rentalmanager.itemtypes.foreach(record=> box.children+= new Label(s"${record.toString}"))
          val back= new Button("Back")
          box.children+= back
          back.onAction = (e: ActionEvent) =>
            box.visible = false
            back.visible = false


        root2.add(itemcheck,200,700)
        itemcheck.onAction=(e: ActionEvent)=>
          val box= new VBox()
          root2.add(box,0,1)
          box.children+= new Label(s"items:")

            rentalmanager.items.foreach(record=> box.children+= new Label(s"${record.toString}"))
          val back= new Button("Back")
          box.children+= back
          back.onAction = (e: ActionEvent) =>
            box.visible = false
            back.visible = false


      val currentsituation= new Button("Current situation")
        root2.add(currentsituation,200,400)
        currentsituation.onAction= (e: ActionEvent) =>
          val box= new VBox()
          root2.add(box,0,1)
          val cs= Label(s"Currently there are ${rentalmanager.items.size} items for rent, ${rentalmanager.renteditems.size} are currently rented. To find out when something will become available please check the rented items page")
          box.children+= cs
          val csback= new Button("back")
            box.children+=csback
            csback.onAction= (e: ActionEvent) =>
            cs.visible = false
            csback.visible = false

    // change the position to information

      val rentalcheck= new Button("Check rented items")
        root2.add(rentalcheck,200,500)
        rentalcheck.onAction=(e: ActionEvent)=>
          val box= new VBox()
          root2.add(box,0,1)
          box.children+= new Label(s"items rented are:")

            rentalmanager.renteditems.foreach(record=> box.children+= new Label(s"${record.toString}"))
          val back= new Button("Back")
          box.children+= back
          back.onAction = (e: ActionEvent) =>
            box.visible = false
            back.visible = false


      val save = new Button("save")
        root2.add(save,200,300)
        save.onAction= (e: ActionEvent) =>
          rentalmanager.saveitems()
          rentalmanager.saveitemrecord()
          rentalmanager.saveRentedItems()
          rentalmanager.save()






    def additem=
      val box=VBox()
      val textinput= new TextField
        root2.add(box,0,1)
        box.children+= Label("Add item")
        box.children+= Label("Write name,description,itemtype,cost,hourly price,dailyprice,weeklyprice,monthlyprice,availablecount")
        box.layoutX= 70
        box.layoutY = 40
        box.children+=textinput
        textinput.onAction = (e: ActionEvent)=> println(textinput.text.value)
      val enter= new Button("Enter")
      val back= new Button("Back")
        box.children+=back
        back.onAction= (e:ActionEvent)=>
          box.visible = false
          back.visible=false

        box.children+=enter
      enter.onAction=(e: ActionEvent)=>
          try
            val value=textinput.text.value.split(",")
            val name=value.head
            val description=value(1)
            val itemtype= value(2)
            val cost =value(3).toDouble
            val hourlyprice=value(4).toDouble
            val dailyprice= value(5).toDouble
            val weeklyprice= value(6).toDouble
            val monthlyprice= value(7).toDouble
            val availalecount= value(8).toInt
            rentalmanager.addItem(new Item(name = name, description = description, itemType = itemtype, price = cost, hourlyPrice = hourlyprice, dailyPrice = dailyprice, weeklyPrice = weeklyprice, monthlyPrice = monthlyprice, availableCount = availalecount))
              box.visible = true
              back.visible = true
            val success= new Label("Item added succesfully")
              box.children+=success
          catch
            //case e:IndexOutOfBoundsException =>  throw Exception("Write the inputs as asked")
            case e:IndexOutOfBoundsException => box.children+= Label("Please write the inputs as asked")
    end additem


    def removeitem=
      val box=VBox()
      val textinput= new TextField
        root2.add(box,0,1)

        box.children+= Label("To remove an item write the item's name and count")
        box.layoutX= 70
        box.layoutY = 40
        box.children+=textinput
        textinput.onAction = (e: ActionEvent)=> println(textinput.text.value)
      val enter= new Button("Enter")
      val back= new Button("Back")
        box.children+=back
        back.onAction= (e:ActionEvent)=>
          box.visible = false
          back.visible=false

        box.children+=enter
      enter.onAction=(e: ActionEvent)=>
        try
          val value= textinput.text.value.split(",")
          val name1= value(0)
          val count= value(1).toInt

          rentalmanager.removeItem(name1,count)

          val success2= new Label("Item removed succesfully")
            box.children+=success2
            box.visible = true
            println("success")

        catch
          case e: IndexOutOfBoundsException => box.children+=Label("Item doesn't exist or it is written wrong")
    end removeitem

    def rentitem=
      val box=VBox()
      val textinput= new TextField
        root2.add(box,0,1)
        box.children+= Label("Rent Item")
        box.children+= Label("Write renter name, item name and count in the format name,item name,count")
        box.layoutX= 70
        box.layoutY = 40
        box.children+=textinput
        textinput.onAction = (e: ActionEvent)=> println(textinput.text.value)

      box.children+=Label("Put the starting date of the rental")
      val date1= new DatePicker((LocalDate.now))
        box.children+=date1

      box.children+=Label("Put the starting time of the rental in the format Hour:Minute")
      val startingtime=new TextField
        box.children+=startingtime




      val date2= new DatePicker(LocalDate.now)
        box.children+=Label("Put the ending date of the rental")
        box.children+=date2

      box.children+=Label("Put the ending time of the rental in format Hour:Minute")
      val endingdate=new TextField
        box.children+=endingdate

      val back= new Button("Back")
        box.children+=back
        back.onAction= (e:ActionEvent)=>
          box.visible = false
          back.visible=false
          endingdate.visible=false
          startingtime.visible= false
          date1.visible= false
          date2.visible= false

      val enter= new Button("Enter")
        box.children+=enter
      enter.onAction=(e: ActionEvent)=>

          try
            val value=textinput.text.value.split(",")
            val renter= new Renter(value.head)
            val item=value(1)
            val count= value(2).toInt
            val rentstart= date1.getValue.atTime(startingtime.text.value.split(":")(0).toInt,startingtime.text.value.split(":")(1).toInt)
            val rentend= date2.getValue.atTime(endingdate.text.value.split(":")(0).toInt,endingdate.text.value.split(":")(1).toInt)
            rentalmanager.rentItem(renter,item,count,rentstart,rentend)
              box.visible = true
              back.visible = true
            val success= new Label("Item rented succesfully")
              box.children+=success
          catch
            //case e:IndexOutOfBoundsException =>  throw Exception("Write the inputs as asked")
            case a:IndexOutOfBoundsException => box.children+= Label("Please write the inputs as asked")
            case b:NumberFormatException => box.children+= Label("put the numbers where as asked")
            case e: IllegalArgumentException => box.children += Label(e.getMessage())
    end rentitem

    def returnitem=
      val box=VBox()
      val textinput= new TextField
        root2.add(box,0,1)
        box.children+= Label("Return Item")
        box.children+= Label("Write renter name, item name, count")
        box.layoutX= 70
        box.layoutY = 40
        box.children+=textinput
        textinput.onAction = (e: ActionEvent)=> println(textinput.text.value)
      val enter= new Button("Enter")
      val back= new Button("Back")
        box.children+=back
        back.onAction= (e:ActionEvent)=>
          box.visible = false
          back.visible=false

        box.children+=enter
      enter.onAction=(e: ActionEvent)=>
        try
          val value=textinput.text.value.split(",")
          val rentalr= rentalmanager.rentalRecords.filter(_.renter.toString==value(0))
          val rent1= rentalr.filter(_.item.name==value(1))
          val rent2= rent1.filter(_.count==value(2).toInt).head
          rentalmanager.returnItem(rent2)
            box.visible = true
            back.visible = true
          val success= new Label("Item returned succesfully")
            box.children+=success
        catch
            //case e:IndexOutOfBoundsException =>  throw Exception("Write the inputs as asked")
          case a:IndexOutOfBoundsException => box.children+= Label("Please write the inputs as asked")
          case b:NumberFormatException => box.children+= Label("put the numbers where it is asked")
          case c:NoSuchElementException => box.children+= Label("Item doesn't exist")
          case e: IllegalArgumentException => box.children += Label(e.getMessage())
    end returnitem

    def addreservation=
      val box=VBox()
      val textinput= new TextField
        root2.add(box,0,1)
        box.children+= Label("Reserve Item")
        box.children+= Label("Write reservers name")
        box.layoutX= 70
        box.layoutY = 40
        box.children+=textinput
        textinput.onAction = (e: ActionEvent)=> println(textinput.text.value)
      box.children+= Label("item names and counts in the format name1,count1,name2,count2")
      val textinput2= new TextField
      box.children+= textinput2
      box.children+=Label("Put the starting date of the reservation")
      val date1= new DatePicker((LocalDate.now))
        box.children+=date1

      box.children+=Label("Put the starting time of the reservation in the format Hour:Minute")
      val startingtime=new TextField
        box.children+=startingtime




      val date2= new DatePicker(LocalDate.now)
        box.children+=Label("Put the ending date of the reservation")
        box.children+=date2

      box.children+=Label("Put the ending time of the reservation in format Hour:Minute")
      val endingdate=new TextField
        box.children+=endingdate

      val back= new Button("Back")
        box.children+=back
        back.onAction= (e:ActionEvent)=>
          box.visible = false
          back.visible=false
          endingdate.visible=false
          startingtime.visible= false
          date1.visible= false
          date2.visible= false

      val enter= new Button("Enter")
        box.children+=enter
      enter.onAction=(e: ActionEvent)=>

          try
            val value=textinput.text.value
            val value2= textinput2.text.value.split(",")
            val renter= new Renter(value)
            val items = ListBuffer[Item]()
            val counts = ListBuffer[Int]()
            textinput2.text.value.split(",").grouped(2).toList.foreach(arr=>
              val item = rentalmanager.items.filter(_.name==arr(0)).head
              val count = arr(1).toInt
              items += item
              counts += count)

            val rentstart= date1.getValue.atTime(startingtime.text.value.split(":")(0).toInt,startingtime.text.value.split(":")(1).toInt)
            val rentend= date2.getValue.atTime(endingdate.text.value.split(":")(0).toInt,endingdate.text.value.split(":")(1).toInt)
            rentalmanager.addReservation(renter,items,counts,rentstart,rentend)
              box.visible = true
              back.visible = true
            val success= new Label("Item reserved succesfully")
              box.children+=success
          catch
            //case e:IndexOutOfBoundsException =>  throw Exception("Write the inputs as asked")
            case a:IndexOutOfBoundsException => box.children+= Label("Please write the inputs as asked")
            case b:NumberFormatException => box.children+= Label("put the numbers where as asked")
            case e: IllegalArgumentException => box.children += Label(e.getMessage())

    end addreservation

    def removereservation=
      val box=VBox()
      val textinput= new TextField
        root2.add(box,0,1)
        box.children+= Label("Remove reservation")
        box.children+= Label("Write reservation name,one item name and its count in the format name,item,count")
        box.layoutX= 70
        box.layoutY = 40
        box.children+=textinput
        textinput.onAction = (e: ActionEvent)=> println(textinput.text.value)
      val enter= new Button("Enter")
      val back= new Button("Back")
        box.children+=back
        back.onAction= (e:ActionEvent)=>
          box.visible = false
          back.visible=false

        box.children+=enter
      enter.onAction=(e: ActionEvent)=>
        try
          val value=textinput.text.value.split(",")
          val renter= value(0)
          val itemName = value(1)
          val count = value(2).toInt
          val rentalr = rentalmanager.reservations.filter(_.renter.name == value(0))
          val rent = rentalr.filter(_.item.exists(_.name == itemName))
          val reservations= rent.filter(_.count.contains(count))

          rentalmanager.removeReservation(reservations.head)
            box.visible = true
            back.visible = true
          val success= new Label("Reservation removed succesfully")
            box.children+=success
        catch
            //case e:IndexOutOfBoundsException =>  throw Exception("Write the inputs as asked")
          case a:IndexOutOfBoundsException => box.children+= Label("Please write the inputs as asked")
          case c:NoSuchElementException => box.children+= Label("Item doesn't exist")
          case e: IllegalArgumentException => box.children += Label(e.getMessage())
    end removereservation

    def comment=
      val box = new VBox()
      root2.add(box,0,1)
      val commentlabel= new Label("Comments")
      box.children+= commentlabel
      val view= new Button("View comments")
      val add= new Button("Add comments")
      val back= new Button("Back")

      val hbox= new HBox(view,add,back)
        box.children+=hbox
      back.onAction= (e: ActionEvent) =>
        box.visible = false
        back.visible = false
        commentlabel.visible = false
      view.onAction= (e: ActionEvent) =>

        val typelabel= Label("Put item type to view comments")
          box.children+=typelabel
        val textbox= new TextField()
          box.children+= textbox

        val enter1= new Button("Enter")
        box.children+= enter1

        val itemlabel= Label("Put item name to view comments")
          box.children+=itemlabel

        val itemtextbox= new TextField()
          box.children+=itemtextbox

        val enter2= new Button("Enter")
        box.children+= enter2

        val renterlabel= Label("Put renter name to view comments")
          box.children+=renterlabel
        val rentertextbox= new TextField()
          box.children+=rentertextbox

        val enter3= new Button("Enter")
        box.children+= enter3

        val back= new Button("Back")
          box.children+=back
          back.onAction= (e:ActionEvent)=>
            box.visible = false
            back.visible=false
            hbox.visible = true

        enter1.onAction= (e: ActionEvent) =>
          val value= textbox.text.value
          val items=rentalmanager.getItemsByType(value)
          items.foreach(item=> box.children += new Label(s"${item.comments.mkString}"))
          if items.isEmpty then box.children+=new Label("Itemtype has no comments")

        enter2.onAction= (e: ActionEvent) =>
          try
            val valueitem= itemtextbox.text.value
            val item= rentalmanager.items.filter(_.name==valueitem).head
            val commentlabel = new Label(s"${item.comments.mkString}")
            if rentalmanager.items.filter(_.name==valueitem).isEmpty then
              box.children += Label("Item doesn't exist or it doesn't have comments")
            else
              box.children += commentlabel
          catch
            case a:IndexOutOfBoundsException => box.children+= Label("Please write the inputs as asked")
            case c:NoSuchElementException => box.children+= Label("Item doesn't exist")
            case e: IllegalArgumentException => box.children += Label(e.getMessage())

        enter3.onAction= (e: ActionEvent) =>
          try
            val valuerenter= rentertextbox.text.value
            val renter= rentalmanager.renters.filter(_.name==valuerenter).head
            val commentlabel = new Label(s"${renter.comments.mkString}")
            if rentalmanager.renters.filter(_.name==valuerenter).isEmpty then
              box.children+= Label("Renter doesn't exist or they don't have comments")
            else
              box.children+= commentlabel
          catch
            case a:IndexOutOfBoundsException => box.children+= Label("Please write the inputs as asked")
            case c:NoSuchElementException => box.children+= Label("Renter doesn't exist")
            case e: IllegalArgumentException => box.children += Label(e.getMessage())

      add.onAction= (e: ActionEvent) =>
        val typerlabel= Label("Put item to add comments")
          box.children+=typerlabel
        val textbox= new TextField()
          box.children+= textbox


        val commentlabels= new Label("Write comment here")
          box.children+= commentlabels

        val commentlabelTextBox= new TextField()
          box.children += commentlabelTextBox


        val enter1= new Button("Enter")
        box.children+= enter1

        val renterLabel= Label("Put renter name to add comment")
          box.children+=renterLabel

        val renterTextBox= new TextField()
          box.children+=renterTextBox


        val rentercommentlabel= Label("Type comment here")
          box.children+=rentercommentlabel


        val rentercommentTextBox= new TextField()
          box.children+=rentercommentTextBox


        val enter2= new Button("Enter")
        box.children+= enter2


        enter1.onAction= (e: ActionEvent) =>
          try
            val values= textbox.text.value
            val commentvalue1= commentlabelTextBox.text.value
            val item=rentalmanager.items.find(_.name==values).head
            item.addComment(commentvalue1)
          catch
            case a:IndexOutOfBoundsException => box.children+= Label("Please write the inputs as asked")
            case c:NoSuchElementException => box.children+= Label("Item doesn't exist")
            case e: IllegalArgumentException => box.children += Label(e.getMessage())
        enter2.onAction= (e: ActionEvent) =>
          try
            val valuerenter= renterTextBox.text.value
            val valuerentercomment= rentercommentTextBox.text.value
            val renter= rentalmanager.renters.filter(_.name==valuerenter).head
            renter.addComment(valuerentercomment)
          catch
            case a:IndexOutOfBoundsException => box.children+= Label("Please write the inputs as asked")
            case c:NoSuchElementException => box.children+= Label("Item doesn't exist")
            case e: IllegalArgumentException => box.children += Label(e.getMessage())



    end comment


//accounting view

      val accounting= new Button("accounting")
        accounting.onAction = (e: ActionEvent) => stage.setScene(scene3)
      val backbutton3=new Button("back")

        backbutton3.onAction= (e: ActionEvent) => stage.setScene(scene)
      val button1= new Button("Income by ItemType")
      val button2= new Button("Income by Item")
      val button3= new Button("Expenses by ItemType")
      val button4= new Button("Expenses by Item")
      val button5= new Button("Gained demand")


      val hbox= new HBox(20,button1,button2,button3,button4,backbutton3, button5)
      root3.children+=hbox
      def gainedDemand=
        val box= new VBox()
        root3.add(box,0,1)
        val rentalRecords = rentalmanager.rentalRecords

        // Count the number of times each item was rented
        val itemCounts = Map[String, Int]()

        rentalRecords.foreach(record =>
          val itemName = record.item.name
          if (itemCounts.contains(itemName)) then
            itemCounts(itemName) += record.count
          else
            itemCounts(itemName) = record.count)

        val chartData = FXCollections.observableArrayList[PieChart.Data]()
        for each <- itemCounts.toMap do
         chartData.add(new Data(s"${each._1}",each._2))

        val chart = new PieChart(chartData)

        chart.title = "Gained demand"
        box.children += chart
        val back = new Button("Back")
        box.children+= back
        back.onAction= (e: ActionEvent) =>
          box.visible = false
          back.visible = false


      def incomebyitemtype=
        val box = new VBox()
        root3.add(box,0,1)
        val label= new Label("Write ItemType")
        val back= new Button("Back")
        box.children+= back
        back.onAction= (e: ActionEvent)=>
          box.visible = false
          back.visible= false
        box.children+= label



        val textbox= new TextField()
        box.children+= textbox

        val enter= new Button("Enter")


        box.children+=Label("Choose starting date")
        val date1= new DatePicker(LocalDate.now)
        box.children+=date1

        box.children+=Label("Choose end date")
        val date2= new DatePicker(LocalDate.now)
        box.children+=date2
        box.children+= enter
        enter.onAction=(e: ActionEvent) =>
          try
            val value = textbox.text.value
            val rentalRecords=rentalmanager.rentalRecords.filter(record=>record.item.itemType==value && !record.rentEnd.isBefore(date1.getValue.atTime(00,00)) && !record.rentStart.isAfter(date2.getValue.atTime(23,59)))
            println(rentalRecords)
            val joku= rentalRecords.map(_.cost).sum

            box.children+= Label(s"Total income for this item type: ${joku.toString}€")
          catch
            case e:IndexOutOfBoundsException => box.children+= Label("Please write the inputs as asked")
            case c:NoSuchElementException => box.children+= Label("Item doesn't exist")
      end incomebyitemtype



      def incomebyitem=
        val box = new VBox()
        root3.add(box,0,1)
        val label= new Label("Write item name")
        val back= new Button("Back")
        box.children+= back
        back.onAction= (e: ActionEvent)=>
          box.visible = false
          back.visible= false

        box.children+= label

        val textbox= new TextField()
        box.children+= textbox
        val enter= new Button("Enter")
        box.children+=Label("Choose starting date")
        val date1= new DatePicker(LocalDate.now)
        box.children+=date1
        box.children+=Label("Choose end date")
        val date2= new DatePicker(LocalDate.now)
        box.children+=date2
        box.children+= enter
        enter.onAction=(e: ActionEvent) =>
          try
            val value = textbox.text.value
            val rentalRecords=rentalmanager.rentalRecords.filter(record=>record.item.name==value && !record.rentEnd.isBefore(date1.getValue.atStartOfDay) && !record.rentStart.isAfter(date2.getValue.atTime(23,59)))
            println(rentalRecords)
            val joku= rentalRecords.map(_.cost).sum

            box.children+= Label(s"Total income for this item: ${joku.toString}€")
          catch
            case e:IndexOutOfBoundsException => box.children+= Label("Please write the inputs as asked")
            case c:NoSuchElementException => box.children+= Label("Item doesn't exist")


      def expensesbyitemtype=
        val box = new VBox()
        root3.add(box,0,1)
        val label= new Label("Write ItemType")
        val back= new Button("Back")
        box.children+= back
        back.onAction= (e: ActionEvent)=>
          box.visible = false
          back.visible= false
        box.children+= label



        val textbox= new TextField()
        box.children+= textbox

        val enter= new Button("Enter")


        box.children+=Label("Choose starting date")
        val date1= new DatePicker(LocalDate.now)
        box.children+=date1

        box.children+=Label("Choose end date")
        val date2= new DatePicker(LocalDate.now)
        box.children+=date2
        box.children+= enter
        enter.onAction=(e: ActionEvent) =>
          try
            val value = textbox.text.value
            val rentalRecords=rentalmanager.rentalRecords.filter(record=>record.item.itemType==value && !record.rentEnd.isBefore(date1.getValue.atStartOfDay) && !record.rentStart.isAfter(date2.getValue.atTime(23,59)))
            println(rentalRecords)
            val joku= rentalRecords.map(_.item.price).sum

            box.children+= Label(s"Total expenses for this item type: ${joku.toString}€")
          catch
            case e:IndexOutOfBoundsException => box.children+= Label("Please write the inputs as asked")
            case c:NoSuchElementException => box.children+= Label("Item doesn't exist")


      def expensebyitem=
        val box = new VBox()
        root3.add(box,0,1)
        val label= new Label("Write item name")
        val back= new Button("Back")
        box.children+= back
        back.onAction= (e: ActionEvent)=>
          box.visible = false
          back.visible= false
        box.children+= label

        val textbox= new TextField()
        box.children+= textbox
        val enter= new Button("Enter")
        box.children+=Label("Choose starting date")
        val date1= new DatePicker(LocalDate.now)
        box.children+=date1
        box.children+=Label("Choose end date")
        val date2= new DatePicker(LocalDate.now)
        box.children+=date2
        box.children+= enter
        enter.onAction=(e: ActionEvent) =>
          try
            val value = textbox.text.value
            val rentalRecords=rentalmanager.rentalRecords.filter(record=>record.item.name==value && !record.rentEnd.isBefore(date1.getValue.atStartOfDay) && !record.rentStart.isAfter(date2.getValue.atTime(23,59)))
            println(rentalRecords)
            val joku= rentalRecords.map(_.item.price).sum

            box.children+= Label(s"Total expense for this item: ${joku.toString}€")
          catch
            case e:IndexOutOfBoundsException => box.children+= Label("Please write the inputs as asked")
            case c:NoSuchElementException => box.children+= Label("Item doesn't exist")

      button1.onAction= (e: ActionEvent) => incomebyitemtype
      button2.onAction= (e: ActionEvent) => incomebyitem
      button3.onAction= (e: ActionEvent) => expensesbyitemtype
      button4.onAction= (e: ActionEvent) => expensebyitem
      button5.onAction= (e: ActionEvent) => gainedDemand
//record view
    def rentalRecord=
      val box= new VBox()
      root4.add(box,0,1)
      box.children+= Label("All rental records:")
      val back= new Button("Back")
      box.children+= back
      back.onAction = (e: ActionEvent) =>
        box.visible = false
        back. visible = false

      rentalmanager.rentalRecords.foreach(record=> box.children+= new Label(s"${record.toString}"))

    end rentalRecord



      val records= new Button("records")
        records.onAction = (e: ActionEvent) => stage.setScene(scene4)
      val backbutton2=new Button("back")
        backbutton2.onAction= (e: ActionEvent) => stage.setScene(scene)
      val recordbutton= new Button("rental records")
        recordbutton.onAction = (e: ActionEvent) => rentalRecord
      val boxforrecordview= new HBox(20,backbutton2,recordbutton)
      root4.children+=boxforrecordview




      val vbox= new HBox(60, items, accounting, records, load)
        root.add(vbox,0,3)
// Item view buttons
      val addItembutton= new Button("Add Item")
      val removeItembutton= new Button("Remove Item")
      val rentItemButton= new Button("Rent Item")
      val returnItem= new Button("Return Item")
      val addReservation= new Button("Add Reservation")
      val removeReservation= new Button("Remove Reservation")
      val comments= new Button("Comments")
      val backbutton= new Button("back")
        backbutton.onAction= (e: ActionEvent) => stage.setScene(scene)
      val boxforitemview= new HBox(20,rentItemButton, addItembutton, removeItembutton,returnItem, addReservation, removeReservation, comments,backbutton)
        root2.add(boxforitemview,0,0)

        addItembutton.onAction= (e: ActionEvent) => additem
        removeItembutton.onAction= (e: ActionEvent) => removeitem
        rentItemButton.onAction= (e: ActionEvent) => rentitem
        returnItem.onAction= (e: ActionEvent) => returnitem
        addReservation.onAction= (e: ActionEvent) => addreservation
        removeReservation.onAction= (e: ActionEvent) => removereservation
        comments.onAction= (e: ActionEvent) => comment






