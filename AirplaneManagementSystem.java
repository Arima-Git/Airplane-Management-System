import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Flight {
    private String flightNumber;
    private String origin;
    private String destination;
    private int availableSeats;

    public Flight(String flightNumber, String origin, String destination, int availableSeats) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.availableSeats = availableSeats;
    }

    // Getters and setters

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return flightNumber + " - " + origin + " to " + destination + " (" + availableSeats + " available seats)";
    }
}

class FlightBooking {
    private Flight flight;
    private String passengerName;

    public FlightBooking(Flight flight, String passengerName) {
        this.flight = flight;
        this.passengerName = passengerName;
    }

    // Getters and setters

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    @Override
    public String toString() {
        return passengerName + " - " + flight.getFlightNumber();
    }
}

class AirplaneManagementSystemGUI extends JFrame {
    private List<Flight> flights;
    private DefaultListModel<Flight> flightListModel;
    private DefaultListModel<FlightBooking> bookingListModel;

    private JLabel flightNumberLabel;
    private JLabel originLabel;
    private JLabel destinationLabel;
    private JLabel availableSeatsLabel;
    private JTextField flightNumberField;
    private JTextField originField;
    private JTextField destinationField;
    private JTextField availableSeatsField;
    private JButton addButton;
    private JList<Flight> flightList;
    private JTextField passengerNameField;
    private JButton bookButton;
    private JList<FlightBooking> bookingList;

    public AirplaneManagementSystemGUI() {
        flights = new ArrayList<>();
        flightListModel = new DefaultListModel<>();
        bookingListModel = new DefaultListModel<>();

        flightNumberLabel = new JLabel("Flight Number:");
        originLabel = new JLabel("Origin:");
        destinationLabel = new JLabel("Destination:");
        availableSeatsLabel = new JLabel("Available Seats:");
        flightNumberField = new JTextField(10);
        originField = new JTextField(10);
        destinationField = new JTextField(10);
        availableSeatsField = new JTextField(10);
        addButton = new JButton("Add Flight");
        flightList = new JList<>(flightListModel);
        passengerNameField = new JTextField(10);
        bookButton = new JButton("Book Flight");
        bookingList = new JList<>(bookingListModel);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String flightNumber = flightNumberField.getText();
                String origin = originField.getText();
                String destination = destinationField.getText();
                int availableSeats = Integer.parseInt(availableSeatsField.getText());

                Flight flight = new Flight(flightNumber, origin, destination, availableSeats);
                flights.add(flight);
                flightListModel.addElement(flight);

                flightNumberField.setText("");
                originField.setText("");
                destinationField.setText("");
                availableSeatsField.setText("");
            }
        });

        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Flight selectedFlight = flightList.getSelectedValue();
                String passengerName = passengerNameField.getText();

                if (selectedFlight != null && passengerName != null && !passengerName.isEmpty()) {
                    FlightBooking booking = new FlightBooking(selectedFlight, passengerName);
                    selectedFlight.setAvailableSeats(selectedFlight.getAvailableSeats() - 1);
                    bookingListModel.addElement(booking);

                    passengerNameField.setText("");
                }
            }
        });

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(5, 5, 5, 5);

        add(flightNumberLabel, gc);
        gc.gridy++;
        add(originLabel, gc);
        gc.gridy++;
        add(destinationLabel, gc);
        gc.gridy++;
        add(availableSeatsLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        add(flightNumberField, gc);
        gc.gridy++;
        add(originField, gc);
        gc.gridy++;
        add(destinationField, gc);
        gc.gridy++;
        add(availableSeatsField, gc);

        gc.gridx = 2;
        gc.gridy = 0;
        add(addButton, gc);

        gc.gridx = 0;
        gc.gridy = 4;
        gc.gridwidth = 3;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(new JScrollPane(flightList), gc);

        gc.gridy++;
        add(new JLabel("Passenger Name:"), gc);
        gc.gridy++;
        add(passengerNameField, gc);
        gc.gridy++;
        add(bookButton, gc);

        gc.gridx = 0;
        gc.gridy = 8;
        gc.gridwidth = 3;
        add(new JScrollPane(bookingList), gc);

        setTitle("Airplane Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

public class AirplaneManagementSystem {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AirplaneManagementSystemGUI();
            }
        });
    }
}
