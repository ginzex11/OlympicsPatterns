package mvc_Olympice_view;


import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mvc_Olympice_listener.OlympiceUIEventsListener;
import mvc_Olympics_model.CompetitionDomains;
import mvc_Olympics_model.CompetitionType;
import mvc_Olympics_model.Referee;
import mvc_Olympics_model.Stadium;

public class OlympiceJavaFx {
	private Vector<OlympiceUIEventsListener> allListeners = new Vector<OlympiceUIEventsListener>();
	private final int SPACING = 10;
	private ListView<String> lvDataView = new ListView<String>();
	private ObservableList<String> olData = FXCollections.observableArrayList();

	public OlympiceJavaFx(Stage theStage) { //
		theStage.setTitle("The Olympia");

		BorderPane bpRoot = new BorderPane();
		bpRoot.setPadding(new Insets(10));

		Button btnAddCountry = new Button("Add Country");
		btnAddCountry.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				bpRoot.getChildren().remove(bpRoot.getCenter());
				bpRoot.setCenter(getAddCountryPane());
				olData.clear();
				for (OlympiceUIEventsListener l : allListeners) {
					olData.addAll(l.getAllCountries());
				}
				lvDataView.setItems(olData);
			}
		});

		
		Button btnAddCompetitor = new Button("Add Competitor");
		btnAddCompetitor.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				bpRoot.getChildren().remove(bpRoot.getCenter());
				bpRoot.setCenter(getAddCompetitorPane());
			}

		});
		
		Button btnAddReferee = new Button("Add Refereee");
		btnAddReferee.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				bpRoot.getChildren().remove(bpRoot.getCenter());
				bpRoot.setCenter(getAddRefereePane());
				olData.clear();
				for (OlympiceUIEventsListener l : allListeners) {
					olData.addAll(l.getAllRefereesNames());
				}
				lvDataView.setItems(olData);
			}

		});

	
		Button btnAddStadium = new Button("Add Stadium");
		btnAddStadium.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				bpRoot.getChildren().remove(bpRoot.getCenter());
				bpRoot.setCenter(getAddStadiumPane());
				olData.clear();
				for (OlympiceUIEventsListener l : allListeners) {
					olData.addAll(l.getAllStadiumsNames());
				}
				lvDataView.setItems(olData);
			}

		});

		
		Button btnAddCompetition = new Button("Add Competition");
		btnAddCompetition.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				bpRoot.getChildren().remove(bpRoot.getCenter());
				bpRoot.setCenter(getAddCompetitionPane());
			}

		});

		Button btnFinish = new Button("finish");
		btnFinish.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				bpRoot.getChildren().remove(bpRoot.getCenter());
				for (OlympiceUIEventsListener l : allListeners) {
					l.finishAndWinningCalculation();
				}
			}

		});

		VBox bpRootLeft = new VBox(btnAddCountry, btnAddCompetitor, btnAddReferee, btnAddStadium, btnAddCompetition,
				btnFinish);
		bpRootLeft.setSpacing(SPACING);
		bpRootLeft.setPrefWidth(110);
		btnAddCountry.setMinWidth(bpRootLeft.getPrefWidth());
		btnAddCompetitor.setMinWidth(bpRootLeft.getPrefWidth());
		btnAddReferee.setMinWidth(bpRootLeft.getPrefWidth());
		btnAddStadium.setMinWidth(bpRootLeft.getPrefWidth());
		btnAddCompetition.setMinWidth(bpRootLeft.getPrefWidth());
		btnFinish.setMinWidth(bpRootLeft.getPrefWidth());
		bpRoot.setLeft(bpRootLeft);

		VBox bpRootRight = new VBox(lvDataView);
		bpRootRight.setSpacing(SPACING);
		bpRootRight.setPrefWidth(150);
		lvDataView.setMinWidth(bpRoot.getPrefWidth());
		bpRoot.setRight(bpRootRight);

		theStage.setScene(new Scene(bpRoot, 600, 500));
		theStage.show();

	}
	
	public void enterNewCountry (String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}
	
 	public void countryIsAlreadyOnTheList(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	protected VBox getAddCompetitorPane() {
		VBox vboxCompetitorMain = new VBox();
		vboxCompetitorMain.setSpacing(10);

		Label lblCompetitorType = new Label("Competition type:");
		ComboBox<String> cmbCompetitorType = new ComboBox<String>();

		cmbCompetitorType.setItems(
				FXCollections.observableArrayList(CompetitionType.SINGLE.toString(), CompetitionType.TEAM.toString()));

		cmbCompetitorType.valueProperty().addListener(new ChangeListener<String>() {
			VBox vboxCompetitorType = null;

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				if (oldValue != null) {
					vboxCompetitorMain.getChildren().remove(vboxCompetitorType);
				}
				olData.clear();
				if (newValue.equals(CompetitionType.SINGLE.toString())) {
					vboxCompetitorType = getSingleCompetitorPane();
					for (OlympiceUIEventsListener l : allListeners) {
						olData.addAll(l.getAllSingleCompetitors());
					}
				} else if (newValue.equals(CompetitionType.TEAM.toString())) {
					vboxCompetitorType = getTeamCompetitorPane();
					for (OlympiceUIEventsListener l : allListeners) {
						olData.addAll(l.getAllTeamCompetitors());
					}
				}
				vboxCompetitorMain.getChildren().add(vboxCompetitorType);

				lvDataView.setItems(olData);

			}

		});

		vboxCompetitorMain.getChildren().addAll(lblCompetitorType, cmbCompetitorType);

		return vboxCompetitorMain;
	}

	protected VBox getTeamCompetitorPane() {
		Label lblCountryName = new Label("Country Name:");
		TextField tfCountryName = new TextField();

		Label lblCompetitionDomains = new Label("Competition domain:");
		ComboBox<String> cmbCompetitionDomains = new ComboBox<String>();
		
		Label lblGender = new Label("gender:");
		ComboBox<String> cmbGender = new ComboBox<String>();
		

		cmbCompetitionDomains.setItems(FXCollections.observableArrayList(CompetitionDomains.HIGH_JUMP.toString(),
				CompetitionDomains.RUNNER.toString()));
		cmbCompetitionDomains.getSelectionModel().selectFirst();
		
		cmbGender.setItems(FXCollections.observableArrayList("women" , "men"));
		cmbGender.getSelectionModel().selectFirst();
		

		Button btnAddTeamCompetitor = new Button("Add");
		btnAddTeamCompetitor.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				for (OlympiceUIEventsListener l : allListeners) {
					l.addTeamToModel(tfCountryName.getText(),
							CompetitionDomains.valueOf(cmbCompetitionDomains.getValue()) , cmbGender.getValue());
				}
			}

		});
		VBox vbox = new VBox(lblCountryName, tfCountryName , lblGender , cmbGender, lblCompetitionDomains , cmbCompetitionDomains,
				btnAddTeamCompetitor);
		vbox.setSpacing(10);
		return vbox;

	}

	protected VBox getSingleCompetitorPane() {
		Vector<CompetitionDomains> domains = new Vector<CompetitionDomains>();
		Label lblName = new Label("Athlete Name:");
		TextField tfName = new TextField();

		Label lblCountryName = new Label("Country Name:");
		TextField tfCountryName = new TextField();

		Label lblCompetitionDomains = new Label("Competition domain:");
		CheckBox cbxHighJump = new CheckBox(CompetitionDomains.HIGH_JUMP.toString());
		cbxHighJump.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					domains.add(CompetitionDomains.HIGH_JUMP);
				} else {
					domains.remove(CompetitionDomains.HIGH_JUMP);
				}
			}
		});
		CheckBox cbxRunner = new CheckBox(CompetitionDomains.RUNNER.toString());
		cbxRunner.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					domains.add(CompetitionDomains.RUNNER);
				} else {
					domains.remove(CompetitionDomains.RUNNER);
				}
			}
		});
		HBox hboxCompetitionTypes = new HBox(cbxHighJump, cbxRunner);
		hboxCompetitionTypes.setSpacing(10);

		Button btnAddTeamCompetitor = new Button("Add");
		btnAddTeamCompetitor.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if (!domains.isEmpty()) {
					for (OlympiceUIEventsListener l : allListeners) {
						l.addPersonalAthleteToModel(tfName.getText(), tfCountryName.getText(), domains);
					}
				}
			}

		});
		VBox vbox = new VBox(lblName, tfName, lblCountryName, tfCountryName, lblCompetitionDomains,
				hboxCompetitionTypes, btnAddTeamCompetitor);
		vbox.setSpacing(10);
		return vbox;
	}

	protected VBox getAddCompetitionPane() {

		ArrayList<Referee> referees = new ArrayList<Referee>();
		ArrayList<Stadium> stadiums = new ArrayList<Stadium>();
		ArrayList<String> refereesNames = new ArrayList<String>();
		ArrayList<String> stadiumsNames = new ArrayList<String>();
		for (OlympiceUIEventsListener l : allListeners) {
			referees.addAll(l.getAllReferees());
			stadiums.addAll(l.getAllStadiums());
		}
		for (Referee r : referees) {
			refereesNames.add(r.getName());
		}
		for (Stadium s : stadiums) {
			stadiumsNames.add(s.getNameStadium());
		}

		Label lblCompetitionTypes = new Label("Competition type:");
		ComboBox<String> cmbCompetitionTypes = new ComboBox<String>();

		cmbCompetitionTypes.setItems(
				FXCollections.observableArrayList(CompetitionType.SINGLE.toString(), CompetitionType.TEAM.toString()));
		cmbCompetitionTypes.getSelectionModel().selectFirst();

		Label lblCompetitionDomains = new Label("Competition domain:");
		ComboBox<String> cmbCompetitionDomains = new ComboBox<String>();

		cmbCompetitionDomains.setItems(FXCollections.observableArrayList(CompetitionDomains.HIGH_JUMP.toString(),
				CompetitionDomains.RUNNER.toString()));
		cmbCompetitionDomains.getSelectionModel().selectFirst();

		Label lblReferee = new Label("Referee:");
		ComboBox<String> cmbReferee = new ComboBox<String>();

		cmbReferee.setItems(FXCollections.observableArrayList(refereesNames));
		cmbReferee.getSelectionModel().selectFirst();

		Label lblStadium = new Label("Stadium:");
		ComboBox<String> cmbStadium = new ComboBox<String>();

		cmbStadium.setItems(FXCollections.observableArrayList(stadiumsNames));
		cmbStadium.getSelectionModel().selectFirst();

		Label lblFirstPlace = new Label("First Place:");
		TextField tfFirstPlace = new TextField();

		Label lblSecondplace = new Label("Second place:");
		TextField tfSecondplace = new TextField();

		Label lblThirdPlace = new Label("Third place:");
		TextField tfThirdPlace = new TextField();

		Button btnAddCompetition = new Button("Add");
		btnAddCompetition.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if (cmbReferee.getValue() == null) {
					JOptionPane.showMessageDialog(null, "There is no judge in the list of judges, please enter a judge first***");
				}
				if (cmbStadium.getValue() == null) {
					JOptionPane.showMessageDialog(null, "There is no stadium List of stadiums, please enter stadium first***");
				}
				for (OlympiceUIEventsListener l : allListeners) {
					l.addCompetitionToModel(CompetitionType.valueOf(cmbCompetitionTypes.getValue()),
							CompetitionDomains.valueOf(cmbCompetitionDomains.getValue()),
							referees.get(cmbReferee.getSelectionModel().getSelectedIndex()),
							stadiums.get(cmbStadium.getSelectionModel().getSelectedIndex()), tfFirstPlace.getText(),
							tfSecondplace.getText(), tfThirdPlace.getText());
				}
			}
		});
		VBox vbox = new VBox(lblCompetitionTypes, cmbCompetitionTypes, lblCompetitionDomains, cmbCompetitionDomains,
				lblReferee, cmbReferee, lblStadium, cmbStadium, lblFirstPlace, tfFirstPlace, lblSecondplace,
				tfSecondplace, lblThirdPlace, tfThirdPlace, btnAddCompetition);
		vbox.setSpacing(10);
		return vbox;
	}

	public void addCompetitorToView(String competitor , int achievements) {
		olData.add(competitor);
		JOptionPane.showMessageDialog(null, "achievements: " + achievements);
	}

	public VBox getAddCountryPane() {
		Label lblCountryName = new Label("Country Name:");
		TextField tfCountryName = new TextField();

		Button btnAddCountry = new Button("Add");
		btnAddCountry.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				for (OlympiceUIEventsListener l : allListeners) {
					l.addCountryToModel(tfCountryName.getText());
				}
			}

		});
		VBox vbox = new VBox(lblCountryName, tfCountryName, btnAddCountry);
		vbox.setSpacing(10);
		return vbox;
	}

	public void addCosuntryToView(String country) {
		olData.add(country);
	}

	public VBox getAddRefereePane() {
		Label lblRefereeName = new Label("Referee name:");
		TextField tfRefereeName = new TextField();

		Label lblCountryName = new Label("Country name:");
		TextField tfCountryName = new TextField();

		Label lblCompetitionDomains = new Label("Competition domain:");
		ComboBox<String> cmbCompetitionDomains = new ComboBox<String>();

		cmbCompetitionDomains.setItems(FXCollections.observableArrayList(CompetitionDomains.HIGH_JUMP.toString(),
				CompetitionDomains.RUNNER.toString()));
		cmbCompetitionDomains.getSelectionModel().selectFirst();

		Button btnAddReferee = new Button("Add");
		btnAddReferee.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				for (OlympiceUIEventsListener l : allListeners) {
					l.addRefereeToModel(tfRefereeName.getText(), tfCountryName.getText(),
							CompetitionDomains.valueOf(cmbCompetitionDomains.getValue()));
				}
			}

		});

		VBox vbox = new VBox(lblRefereeName, tfRefereeName, lblCountryName, tfCountryName, lblCompetitionDomains,
				cmbCompetitionDomains, btnAddReferee);
		vbox.setSpacing(10);
		return vbox;
	}

	public void addRefereeToView(String refereeName , int achievements) {
		olData.add(refereeName);
		JOptionPane.showMessageDialog(null, "achievements: " + achievements);
	}
	
	public void theJudgeIsOnTheList (String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}
	
	public void thisStadiumIsAlreadyOnTheList (String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	public VBox getAddStadiumPane() {
	
		Label lblnumberOfSeats = new Label("Number Of seats:");
		TextField tfNumberOfSeats = new TextField();

		Label lblNameStadium = new Label("Name stadium:");
		TextField tfNameStadium = new TextField();

		Label lblLocation = new Label("Location:");
		TextField tfLocation = new TextField();
		
		VBox vboxStateMain = new VBox();
		vboxStateMain.setSpacing(20);
		
		Label lblStateType = new Label("state type:");
		ComboBox<String> cmbStateType = new ComboBox<String>();

		cmbStateType.setItems(
				FXCollections.observableArrayList("Competition", "training"));

		cmbStateType.valueProperty().addListener(new ChangeListener<String>() {
			HBox hboxStateType = null;

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				if (oldValue != null) {
					vboxStateMain.getChildren().remove(hboxStateType);
				}
				olData.clear();
				if (newValue.equals("Competition")) {
					hboxStateType = getStateCompetition(tfNumberOfSeats.getText(), tfNameStadium.getText(), tfLocation.getText());
					
				} else if (newValue.equals("training")) {
					hboxStateType = getStateTraining(tfNumberOfSeats.getText(), tfNameStadium.getText(), tfLocation.getText());
				}
				vboxStateMain.getChildren().add(hboxStateType);

				lvDataView.setItems(olData);

			}
		});
		
		vboxStateMain.getChildren().addAll(lblnumberOfSeats, tfNumberOfSeats, lblNameStadium, tfNameStadium, lblLocation, tfLocation, lblStateType , cmbStateType
	);
		vboxStateMain.setSpacing(10);
		return vboxStateMain;
	}
	

	protected HBox getStateCompetition(String tfNumberOfSeats,String tfNameStadium, String tfLocation) {
		
		ArrayList<Referee> referees = new ArrayList<Referee>();
		ArrayList<String> refereeNames = new ArrayList<String>();
		for (OlympiceUIEventsListener l : allListeners) {
			referees.addAll(l.getAllReferees());
			
		}
		for (Referee r : referees) {
			refereeNames.add(r.getName());
		}

		Label lblReferee1 = new Label("Referee1:");
		ComboBox<String> cmbReferee1 = new ComboBox<String>();

		Label lblReferee2 = new Label("Referee2:");
		ComboBox<String> cmbReferee2 = new ComboBox<String>();
		
		Label lblReferee3 = new Label("Referee3:");
		ComboBox<String> cmbReferee3 = new ComboBox<String>();

		cmbReferee1.setItems(FXCollections.observableArrayList(refereeNames));
		cmbReferee1.getSelectionModel().selectFirst();
		
		cmbReferee2.setItems(FXCollections.observableArrayList(refereeNames));
		cmbReferee2.getSelectionModel().selectFirst();
		
		cmbReferee3.setItems(FXCollections.observableArrayList(refereeNames));
		cmbReferee3.getSelectionModel().selectFirst();
		
		
		Button btnAddStadium = new Button("Add");
		btnAddStadium.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					int tfNumberOfSeats1 = Integer.parseInt(tfNumberOfSeats);
					for (OlympiceUIEventsListener l : allListeners) {
						l.addStadiumToModel(tfNumberOfSeats1, tfNameStadium, tfLocation);
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Please enter numbers only in \"Number of seats\"");
				}
			}
		});
		
		HBox hbox = new HBox(lblReferee1 , cmbReferee1 , lblReferee2, cmbReferee2 , lblReferee3, cmbReferee3 , btnAddStadium);
		hbox.setSpacing(10);
		return hbox;
	}

	protected HBox getStateTraining(String tfNumberOfSeats,String tfNameStadium, String tfLocation) {
				
		ArrayList<Referee> referees = new ArrayList<Referee>();
		ArrayList<String> refereeNames = new ArrayList<String>();
		for (OlympiceUIEventsListener l : allListeners) {
			referees.addAll(l.getAllReferees());
			
		}
		for (Referee r : referees) {
			refereeNames.add(r.getName());
		}

		Label lblReferee1 = new Label("Referee1:");
		ComboBox<String> cmbReferee1 = new ComboBox<String>();

		Label lblReferee2 = new Label("Referee2:");
		ComboBox<String> cmbReferee2 = new ComboBox<String>();

		cmbReferee1.setItems(FXCollections.observableArrayList(refereeNames));
		cmbReferee1.getSelectionModel().selectFirst();
		
		cmbReferee2.setItems(FXCollections.observableArrayList(refereeNames));
		cmbReferee2.getSelectionModel().selectFirst();
		
		Button btnAddStadium = new Button("Add");
		btnAddStadium.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					int tfNumberOfSeats1 = Integer.parseInt(tfNumberOfSeats);
					for (OlympiceUIEventsListener l : allListeners) {
						l.addStadiumToModel(tfNumberOfSeats1, tfNameStadium, tfLocation);
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Please enter numbers only in \"Number of seats\"");
				}
			}
		});
		
		HBox hbox = new HBox(lblReferee1 , cmbReferee1 , lblReferee2 , cmbReferee2 , btnAddStadium);
		hbox.setSpacing(10);
		return hbox;
	}

	public void addStadiumToView(String nameStadium , int numOfSpectators) {
		olData.add(nameStadium);
		JOptionPane.showMessageDialog(null, "number Of Spectators: " + numOfSpectators);
	}

	public void registerListener(OlympiceUIEventsListener newListener) {
		allListeners.add(newListener);
	}

	public void addWinnersOfTheCompetitionToView(String firstPlaceInTheOlympics, String secondPlaceInTheOlympics,
			String thirdPlaceInTheOlympics) {
		JOptionPane.showMessageDialog(null, "First place:" + firstPlaceInTheOlympics + "\n Second place:" + 
			secondPlaceInTheOlympics + "\n Third place:" + thirdPlaceInTheOlympics);
				
	}
}
