package ru.sgt1503.packerPro.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sgt1503.packerPro.Resolver.Algorithm;
import ru.sgt1503.packerPro.entity.Container;
import ru.sgt1503.packerPro.entity.Thing;
import ru.sgt1503.packerPro.service.ChromosomeService;
import ru.sgt1503.packerPro.service.ContainerService;
import ru.sgt1503.packerPro.service.PlacementService;
import ru.sgt1503.packerPro.service.ThingService;

import java.util.ArrayList;


@Component
public class SimpleController {

	private final ObservableList<Container> ContainerData = FXCollections.observableArrayList();
	private final ObservableList<Thing> thingData = FXCollections.observableArrayList();

	public ContainerService containerService;
	public ThingService thingService;
	private final PlacementService placementService;
	private final ChromosomeService chromosomeService;

	@Autowired
	public SimpleController(ContainerService containerService, ThingService thingService, PlacementService placementService,
							ChromosomeService chromosomeService) {
		this.containerService = containerService;
		this.thingService = thingService;
		this.placementService = placementService;
		this.chromosomeService = chromosomeService;
	}



	@FXML
	public Button addContainer;


	@FXML
	public Button deleteContainer;

	@FXML
	public TextField nameContainer;

	@FXML
	public TextField widthContainer;

	@FXML
	public TextField lengthContainer;

	@FXML
	public TextField heightContainer;


	@FXML
	public TextField nameThing;

	@FXML
	public TextField widthThing;

	@FXML
	public TextField lengthThing;

	@FXML
	public TextField heightThing;

	@FXML
	public TableView<Container> tableContainer;

	@FXML
	public TableColumn<Container, String> nameColumnContainer;

	@FXML
	public TableColumn<Container, Double> widthColumnContainer;

	@FXML
	public TableColumn<Container, Double> lengthColumnContainer;

	@FXML
	public TableColumn<Container, Double> heightColumnContainer;


	@FXML
	public Button addThing;



	@FXML
	public Button deleteThing;


	@FXML
	public TableView<Thing> tableThing;

	@FXML
	public TableColumn<Thing, Container> nameContainerColumnThing;

	@FXML
	public TableColumn<Thing, String> nameColumnThing;

	@FXML
	public TableColumn<Thing, Double> widthColumnThing;

	@FXML
	public TableColumn<Thing, Double> lengthColumnThing;

	@FXML
	public TableColumn<Thing, Double> heightColumnThing;




	@FXML
	private void initialize() {




		// ?????????????????????????? ?????? ?? ???????????????? ?????????????? ???????????? ???????????????? ?? ???????????????? ?????????????? Container
		nameColumnContainer.setCellValueFactory(new PropertyValueFactory<Container, String>("name"));
		widthColumnContainer.setCellValueFactory(new PropertyValueFactory<Container, Double>("width"));
		lengthColumnContainer.setCellValueFactory(new PropertyValueFactory<Container, Double>("length"));
		heightColumnContainer.setCellValueFactory(new PropertyValueFactory<Container, Double>("height"));


		// ?????????????????? ?????????????? Container ?????????????? ???? ?????????? ??????????
		if (containerService.getTableSize() != 0) {
			ContainerData.addAll(containerService.getAll());
		}
		tableContainer.setItems(ContainerData);
		tableContainer.setEditable(true);
		nameColumnContainer.setCellFactory(TextFieldTableCell.forTableColumn());
		widthColumnContainer.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		lengthColumnContainer.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		heightColumnContainer.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));





		// ?????????????????????????? ?????? ?? ???????????????? ?????????????? ???????????? ???????????????? ?? ???????????????? ?????????????? Thing
		nameColumnThing.setCellValueFactory(new PropertyValueFactory<Thing, String>("name"));
		widthColumnThing.setCellValueFactory(new PropertyValueFactory<Thing, Double>("width"));
		lengthColumnThing.setCellValueFactory(new PropertyValueFactory<Thing, Double>("length"));
		heightColumnThing.setCellValueFactory(new PropertyValueFactory<Thing, Double>("height"));

		System.out.println(thingService.getTableSize());
//		 ?????????????????? ?????????????? Thing ?????????????? ???? ?????????? ??????????
		if (thingService.getTableSize() != 0) {
			thingData.addAll(thingService.getAll());
		}
		tableThing.setItems(thingData);
		tableThing.setEditable(true);
		nameColumnThing.setCellFactory(TextFieldTableCell.forTableColumn());
		widthColumnThing.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		lengthColumnThing.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		heightColumnThing.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
	}

	@FXML
	public void addContainerClicked(ActionEvent actionEvent) {
		if (!(nameContainer.getText().isEmpty() && widthContainer.getText().isEmpty()
				&& lengthContainer.getText().isEmpty() && heightContainer.getText().isEmpty())) {
			Container container = new Container(
					nameContainer.getText(),
					Double.parseDouble(widthContainer.getText()),
					Double.parseDouble(lengthContainer.getText()),
					Double.parseDouble(heightContainer.getText()));
			ContainerData.add(containerService.createContainer(container));
		}
	}

	public void deleteContainerClicked(ActionEvent actionEvent) {
		int row = tableContainer.getSelectionModel().getSelectedIndex();
		tableContainer.getItems().remove(row);
	}

	public void addThingClicked(ActionEvent actionEvent) {
		if (!(nameThing.getText().isEmpty() && widthThing.getText().isEmpty()
				&& lengthThing.getText().isEmpty() && heightThing.getText().isEmpty())) {
			ArrayList<Double> defaultPosition = new ArrayList<>();
			for (int i = 0; i < 3; i++) {
				defaultPosition.add(0.0);
			}
			thingData.add(
					thingService.createThing(
							new Thing(
									nameThing.getText(),
									Double.parseDouble(widthThing.getText()),
									Double.parseDouble(lengthThing.getText()),
									Double.parseDouble(heightThing.getText()),
									defaultPosition,
									null)));
		}

	}


	public void deleteThingClicked(ActionEvent actionEvent) {
		int row = tableThing.getSelectionModel().getSelectedIndex();
		tableThing.getItems().remove(row);
		thingService.deleteThing(tableThing.getSelectionModel().getSelectedItem());
	}


	public void onEdtChangeContainerName(TableColumn.CellEditEvent<Container, String> containerStringCellEditEvent) {
		Container container = tableContainer.getSelectionModel().getSelectedItem();
		container.setName(containerStringCellEditEvent.getNewValue());
		containerService.editContainer(container);
	}

	public void onEdtChangeContainerWidth(TableColumn.CellEditEvent<Container, Double> containerDoubleCellEditEvent) {
		Container container = tableContainer.getSelectionModel().getSelectedItem();
		container.setWidth(containerDoubleCellEditEvent.getNewValue());
		containerService.editContainer(container);
	}
	public void onEdtChangeContainerHeight(TableColumn.CellEditEvent<Container, Double> containerDoubleCellEditEvent) {
		Container container = tableContainer.getSelectionModel().getSelectedItem();
		container.setHeight(containerDoubleCellEditEvent.getNewValue());
		containerService.editContainer(container);
	}
	public void onEdtChangeContainerLength(TableColumn.CellEditEvent<Container, Double> containerDoubleCellEditEvent) {
		Container container = tableContainer.getSelectionModel().getSelectedItem();
		container.setLength(containerDoubleCellEditEvent.getNewValue());
		containerService.editContainer(container);
	}

	public void onEdtChangeThingName(TableColumn.CellEditEvent<Thing, String> thingStringCellEditEvent) {
		Thing thing = tableThing.getSelectionModel().getSelectedItem();
		thing.setName(thingStringCellEditEvent.getNewValue());
		thingService.editThing(thing);
	}

	public void onEdtChangeThingWidth(TableColumn.CellEditEvent<Thing, Double> thingDoubleCellEditEvent) {
		Thing thing = tableThing.getSelectionModel().getSelectedItem();
		thing.setWidth(thingDoubleCellEditEvent.getNewValue());
		thingService.editThing(thing);
	}

	public void onEdtChangeThingLength(TableColumn.CellEditEvent<Thing, Double> thingDoubleCellEditEvent) {
		Thing thing = tableThing.getSelectionModel().getSelectedItem();
		thing.setLength(thingDoubleCellEditEvent.getNewValue());
		thingService.editThing(thing);
	}

	public void onEdtChangeThingHeight(TableColumn.CellEditEvent<Thing, Double> thingDoubleCellEditEvent) {
		Thing thing = tableThing.getSelectionModel().getSelectedItem();
		thing.setHeight(thingDoubleCellEditEvent.getNewValue());
		thingService.editThing(thing);
	}

	public void sortClicked(ActionEvent actionEvent) {
		Algorithm algorithm = new Algorithm(containerService, thingService, placementService, chromosomeService);
		algorithm.resolve();
	}

	public void onRefreshTableThingClicked(ActionEvent actionEvent) {
		tableThing.getItems().removeAll();
		if (thingService.getTableSize() != 0) {
			thingData.addAll(thingService.getAll());
		}
		tableThing.setItems(thingData);
		tableThing.setEditable(true);
		nameColumnThing.setCellFactory(TextFieldTableCell.forTableColumn());
		widthColumnThing.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		lengthColumnThing.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		heightColumnThing.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
	}
}
