<?xml version="1.0" encoding="UTF-8"?>


<?import javafx.geometry.Insets?>

<?import javafx.scene.control.Button?>

<?import javafx.scene.control.DatePicker?>

<?import javafx.scene.control.Label?>

<?import javafx.scene.control.TextField?>

<?import javafx.scene.layout.AnchorPane?>

<?import javafx.scene.layout.HBox?>

<?import javafx.scene.layout.Pane?>

<?import javafx.scene.layout.VBox?>

<?import javafx.scene.text.Font?>

<?import org.kordamp.ikonli.javafx.FontIcon?>


<AnchorPane maxHeight="-Infinity" maxWidth="-Infinity" minHeight="-Infinity" minWidth="-Infinity" prefHeight="713.0" prefWidth="990.0" style="-fx-background-color: white; -fx-border-radius: 10;" xmlns="http://javafx.com/javafx/17" xmlns:fx="http://javafx.com/fxml/1" fx:controller="com.proj.comp2171project.DashController">

    <children>

        <VBox layoutY="69.0" prefHeight="644.0" prefWidth="160.0" style="-fx-background-color: #152132;" stylesheets="@../../../dashstyles.css" AnchorPane.bottomAnchor="0.0" AnchorPane.topAnchor="69.0">

            <children>

                <Pane prefHeight="66.0" prefWidth="160.0" style="-fx-background-color: #242C3C;" />

                <Button fx:id="btnHome" alignment="BASELINE_LEFT" mnemonicParsing="false" prefHeight="44.0" prefWidth="160.0" stylesheets="@../../../dashstyles.css" text="Home" textFill="WHITE">

                    <opaqueInsets>

                        <Insets />

                    </opaqueInsets>

                    <padding>

                        <Insets left="30.0" />

                    </padding>

                    <font>

                        <Font size="19.0" />

                    </font>

                    <graphic>

                        <FontIcon iconColor="WHITE" iconLiteral="fas-home" iconSize="20" />

                    </graphic>

                </Button>

                <Button fx:id="btnNew" alignment="BASELINE_LEFT" mnemonicParsing="false" prefHeight="56.0" prefWidth="160.0" stylesheets="@../../../dashstyles.css" text="New Record" textFill="WHITE">

                    <opaqueInsets>

                        <Insets />

                    </opaqueInsets>

                    <padding>

                        <Insets left="30.0" />

                    </padding>

                    <font>

                        <Font size="16.0" />

                    </font>

                    <graphic>

                        <FontIcon iconColor="WHITE" iconLiteral="fltfal-add-square-24" iconSize="24" />

                    </graphic>

                </Button>

                <Button fx:id="btnEdit" alignment="BASELINE_LEFT" mnemonicParsing="false" prefHeight="56.0" prefWidth="160.0" stylesheets="@../../../dashstyles.css" text="Edit Record" textFill="WHITE">

                    <opaqueInsets>

                        <Insets />

                    </opaqueInsets>

                    <padding>

                        <Insets left="30.0" />

                    </padding>

                    <font>

                        <Font size="16.0" />

                    </font>

                    <graphic>

                        <FontIcon iconColor="WHITE" iconLiteral="fas-user-edit" iconSize="20" />

                    </graphic>

                </Button>

                <Button fx:id="btnSchedule" alignment="BASELINE_LEFT" mnemonicParsing="false" prefHeight="56.0" prefWidth="160.0" stylesheets="@../../../dashstyles.css" text="Schedule " textFill="WHITE">

                    <opaqueInsets>

                        <Insets />

                    </opaqueInsets>

                    <padding>

                        <Insets left="30.0" />

                    </padding>

                    <font>

                        <Font size="16.0" />

                    </font>

                    <graphic>

                        <FontIcon iconColor="WHITE" iconLiteral="far-calendar-alt" iconSize="20" />

                    </graphic>

                </Button>

                <Button fx:id="btnNotify" alignment="BASELINE_LEFT" mnemonicParsing="false" prefHeight="56.0" prefWidth="160.0" stylesheets="@../../../dashstyles.css" text="Send Email" textFill="WHITE">

                    <opaqueInsets>

                        <Insets />

                    </opaqueInsets>

                    <padding>

                        <Insets left="30.0" />

                    </padding>

                    <font>

                        <Font size="16.0" />

                    </font>

                    <graphic>

                        <FontIcon iconColor="WHITE" iconLiteral="fas-mail-bulk" iconSize="20" />

                    </graphic>

                </Button>

                <Button fx:id="btnAudit" alignment="BASELINE_LEFT" mnemonicParsing="false" prefHeight="56.0" prefWidth="160.0" stylesheets="@../../../dashstyles.css" text=" Audit" textFill="WHITE">

                    <opaqueInsets>

                        <Insets />

                    </opaqueInsets>

                    <padding>

                        <Insets left="30.0" />

                    </padding>

                    <font>

                        <Font size="16.0" />

                    </font>

                    <graphic>

                        <FontIcon iconColor="WHITE" iconLiteral="fas-network-wired" iconSize="20" />

                    </graphic>

                </Button>

                <Button fx:id="btnUsers" alignment="BASELINE_LEFT" mnemonicParsing="false" prefHeight="56.0" prefWidth="160.0" stylesheets="@../../../dashstyles.css" text="Users" textFill="WHITE">

                    <opaqueInsets>

                        <Insets />

                    </opaqueInsets>

                    <padding>

                        <Insets left="30.0" />

                    </padding>

                    <font>

                        <Font size="16.0" />

                    </font>

                    <graphic>

                        <FontIcon iconColor="WHITE" iconLiteral="fas-users" iconSize="20" />

                    </graphic>

                </Button>

                <Pane prefHeight="157.0" prefWidth="160.0" style="-fx-background-color: #242C3C;" />

                <Button fx:id="btnAudit1" alignment="BASELINE_LEFT" mnemonicParsing="false" prefHeight="45.0" prefWidth="160.0" stylesheets="@../../../dashstyles.css" text="Logout" textFill="WHITE">

                    <opaqueInsets>

                        <Insets />

                    </opaqueInsets>

                    <padding>

                        <Insets left="30.0" />

                    </padding>

                    <font>

                        <Font size="16.0" />

                    </font>

                    <graphic>

                        <FontIcon iconColor="WHITE" iconLiteral="fas-power-off" iconSize="20" />

                    </graphic>

                </Button>

            </children>

        </VBox>

        <FontIcon layoutX="217.0" layoutY="116.0" />

        <HBox prefHeight="70.0" prefWidth="990.0" style="-fx-background-color: #152132;">

            <children>

                <Pane prefHeight="70.0" prefWidth="990.0" style="-fx-background-color: #152132;">

                    <children>

                        <FontIcon iconColor="WHITE" iconLiteral="fas-bezier-curve" iconSize="35" layoutX="25.0" layoutY="49.0" text="" />

                        <Label layoutX="82.0" layoutY="18.0" style="-fx-font-weight: bold; -fx-font-family: 'Brush Script MT', cursive;" text="GMDB SYSTEM" textFill="WHITE">

                            <font>

                                <Font size="24.0" />

                            </font>

                        </Label>

                    </children>

                </Pane>

            </children>

        </HBox>

      <TextField fx:id="tfBatchNumber" layoutX="246.0" layoutY="219.0" prefHeight="25.0" prefWidth="191.0" />

      <Label layoutX="246.0" layoutY="196.0" text="Batch Number" />

      <DatePicker fx:id="tfTrainingDate" layoutX="571.0" layoutY="218.0" prefHeight="25.0" prefWidth="205.0" />

      <Label layoutX="571.0" layoutY="195.0" text="Date of Training" />

      <TextField fx:id="tfTrainingName" layoutX="246.0" layoutY="308.0" prefHeight="25.0" prefWidth="191.0" />

      <Label layoutX="246.0" layoutY="281.0" text="Training Name" />

      <TextField fx:id="tfTrainingLocation" layoutX="571.0" layoutY="309.0" prefHeight="25.0" prefWidth="205.0" />

      <Label layoutX="571.0" layoutY="282.0" text="Training Location" />

      <Label layoutX="246.0" layoutY="111.0" text="Schedule Training">

         <font>

            <Font size="25.0" />

         </font>

      </Label>

      <Button fx:id="btnSave" layoutX="246.0" layoutY="391.0" mnemonicParsing="false" prefHeight="25.0" prefWidth="535.0" text="Save" textFill="WHITE" />

    </children>

</AnchorPane>
