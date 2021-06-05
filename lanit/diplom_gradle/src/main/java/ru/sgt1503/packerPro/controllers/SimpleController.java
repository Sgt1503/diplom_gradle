package ru.sgt1503.packerPro.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sgt1503.packerPro.Resolver.ContainerResolver;
import ru.sgt1503.packerPro.entity.Container;
import ru.sgt1503.packerPro.entity.Thing;
import ru.sgt1503.packerPro.service.ContainerService;
import ru.sgt1503.packerPro.service.ThingService;

import java.util.ArrayList;


@Component
public class SimpleController {

	private final ObservableList<Container> ContainerData = FXCollections.observableArrayList();
	private final ObservableList<Thing> thingData = FXCollections.observableArrayList();

	public ContainerService containerService;
	public ThingService thingService;
	public Button RefreshTableThing;

	@Autowired
	public SimpleController(ContainerService containerService, ThingService thingService) {
		this.containerService = containerService;
		this.thingService = thingService;
	}

	@FXML
	public Button addContainer;

	@FXML
	public Button editContainer;

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
	public ComboBox<String> comboboxContainerName;

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
	public Button editThing;

	@FXML
	public Button deleteThing;

	@FXML
	public Button RefreshContainerComboBox;

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




		// устанавливаем тип и значение которое должно хранится в колонках Таблицы Container
		nameColumnContainer.setCellValueFactory(new PropertyValueFactory<Container, String>("name"));
		widthColumnContainer.setCellValueFactory(new PropertyValueFactory<Container, Double>("width"));
		lengthColumnContainer.setCellValueFactory(new PropertyValueFactory<Container, Double>("length"));
		heightColumnContainer.setCellValueFactory(new PropertyValueFactory<Container, Double>("height"));


		// заполняем таблицу Container данными из файла дампа
		if (containerService.getTableSize() != 0) {
			ContainerData.addAll(containerService.getAll());
		}
		tableContainer.setItems(ContainerData);
		tableContainer.setEditable(true);
		nameColumnContainer.setCellFactory(TextFieldTableCell.forTableColumn());
		widthColumnContainer.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		lengthColumnContainer.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		heightColumnContainer.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));





		// устанавливаем тип и значение которое должно хранится в колонках Таблицы Thing
		nameContainerColumnThing.setCellValueFactory(new PropertyValueFactory<Thing, Container>("container"));
		nameColumnThing.setCellValueFactory(new PropertyValueFactory<Thing, String>("name"));
		widthColumnThing.setCellValueFactory(new PropertyValueFactory<Thing, Double>("width"));
		lengthColumnThing.setCellValueFactory(new PropertyValueFactory<Thing, Double>("length"));
		heightColumnThing.setCellValueFactory(new PropertyValueFactory<Thing, Double>("height"));

		System.out.println(thingService.getTableSize());
//		 заполняем таблицу Thing данными из файла дампа
		if (thingService.getTableSize() != 0) {
			thingData.addAll(thingService.getAll());
		}
		tableThing.setItems(thingData);
		tableThing.setEditable(true);
		nameContainerColumnThing.setCellFactory(ComboBoxTableCell.forTableColumn());
		nameColumnThing.setCellFactory(TextFieldTableCell.forTableColumn());
		widthColumnThing.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		lengthColumnThing.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		heightColumnThing.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

		//Обновление данных в Combobox
		refreshData();

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
		refreshData();
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
								containerService.getContainerByName(comboboxContainerName.getSelectionModel().getSelectedItem()))));
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

	public void onEdtChangeThingNameContainer(TableColumn.CellEditEvent<Thing, String> thingStringCellEditEvent) {
		Thing thing = tableThing.getSelectionModel().getSelectedItem();
		thing.setName(thingStringCellEditEvent.getNewValue());
		thingService.editThing(thing);
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

	public void onClickcomboboxContainerName(ActionEvent actionEvent) {
		System.out.println(comboboxContainerName.getItems().size());
		if (containerService.getTableSize() != comboboxContainerName.getItems().size()) {
			comboboxContainerName.getItems().removeAll();
			comboboxContainerName.getItems().setAll(containerService.getAllNames());
		}
	}

	public void refreshData(){
		if (containerService.getTableSize() != comboboxContainerName.getItems().size()) {
			comboboxContainerName.getItems().removeAll();
			comboboxContainerName.getItems().setAll(containerService.getAllNames());
		}
	}

	public void sortClicked(ActionEvent actionEvent) {
		ContainerResolver containerResolver = new ContainerResolver(containerService, thingService);
		containerResolver.solvePackingProblem();
	}

	public void onRefreshTableThingClicked(ActionEvent actionEvent) {
		tableThing.getItems().removeAll();
		if (thingService.getTableSize() != 0) {
			thingData.addAll(thingService.getAll());
		}
		tableThing.setItems(thingData);
		tableThing.setEditable(true);
		nameContainerColumnThing.setCellFactory(ComboBoxTableCell.forTableColumn());
		nameColumnThing.setCellFactory(TextFieldTableCell.forTableColumn());
		widthColumnThing.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		lengthColumnThing.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		heightColumnThing.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
	}
}
