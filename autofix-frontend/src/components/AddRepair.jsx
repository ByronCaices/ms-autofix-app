import { useState, useEffect } from "react";
import { Link, useParams, useNavigate } from "react-router-dom";
import repairService from "../services/repair.service";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import { format, set } from "date-fns";
import Button from "@mui/material/Button";
import FormControl from "@mui/material/FormControl";
import MenuItem from "@mui/material/MenuItem";
import SaveIcon from "@mui/icons-material/Save";
import dayjs from "dayjs";

const AddRepair = () => {
  const { id } = useParams();
  const [repairPrice, setRepairPrice] = useState("");
  const [repairType, setRepairType] = useState("");
  const [repairCode, setRepairCode] = useState("");
  const [plate, setPlate] = useState("");
  const [bodywork, setBodywork] = useState("");
  const [engine, setEngine] = useState("");
  const [brand, setBrand] = useState("");
  const [mileage, setMileage] = useState("");
  const [discRegClient, setDiscRegClient] = useState(0);
  const [discMonThu, setDiscMonThu] = useState(0);
  const [discBonus, setDiscBonus] = useState(0);
  const [surchCarage, setSurchCarage] = useState(0);
  const [surchCarmileage, setSurchCarmileage] = useState(0);
  const [surchDelay, setSurchDelay] = useState(0);
  const [checkinDate, setCheckinDate] = useState("");
  const [finishDate, setFinishDate] = useState("");
  const [checkoutDate, setCheckoutDate] = useState("");
  const [iva, setIva] = useState(0);
  const [totalAmount, setTotalAmount] = useState(0);
  const [titleRepairForm, setTitleRepairForm] = useState("");

  const navigate = useNavigate();

  const saveRepair = (e) => {
    e.preventDefault();
    console.log("SAVE");

    const repair = {
      repairPrice,
      repairType,
      repairCode,
      plate,
      bodywork,
      engine,
      brand,
      mileage,
      discRegClient,
      discMonThu,
      discBonus,
      surchCarage,
      surchCarmileage,
      surchDelay,
      checkinDate,
      finishDate: finishDate,
      checkoutDate,
      iva,
      totalAmount,
    };

    console.log("RRRRRRR");
    console.log(repair);
    console.log("RRRRRRR");

    repairService
      .create(repair)
      .then((response) => {
        console.log("Repair has been added.", response.data);
        navigate("/repair/list");
      })
      .catch((error) => {
        console.log(repair);
        console.log(
          ":(\nAn error ocurred while trying tu add new repair.",
          error
        );
        window.alert(
          "An error ocurred while trying tu add new repair. Check if your vehicle is registered in the system."
        );
      });
  };

  useEffect(() => {
    // repair code is the concatenation of plate and mileage
    //setRepairCode(plate + mileage);
    // save checkin datetime
    //setCheckinDate(dayjs());
    console.log("useEffect");
    setTitleRepairForm("Register New Repair");
  }, []);

  return (
    <Box
      display="flex"
      flexDirection="column"
      alignItems="center"
      justifyContent="center"
      component="form"
    >
      <h2>{titleRepairForm}</h2>
      Make sure the car is registered in the system to add a repair.
      <form>
        <FormControl fullWidth>
          <TextField
            id="plate"
            label="Plate"
            value={plate}
            variant="standard"
            onChange={(e) => setPlate(e.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="mileage"
            label="Mileage"
            type="number"
            value={mileage}
            variant="standard"
            onChange={(e) => setMileage(e.target.value)}
          />
        </FormControl>

        <FormControl fullWidth>
          <TextField
            id="repairtype"
            label="Repair Type"
            value={repairType}
            select
            variant="standard"
            defaultValue="diesel"
            onChange={(e) => setRepairType(e.target.value)}
            style={{ width: "25%" }}
          >
            <MenuItem value={1}>1. Brake System</MenuItem>
            <MenuItem value={2}>2. Cooling System</MenuItem>
            <MenuItem value={3}>3. Engine Repairs</MenuItem>
            <MenuItem value={4}>4. Transmission Repairs</MenuItem>
            <MenuItem value={5}>5. Electrical System</MenuItem>
            <MenuItem value={6}>6. Exhaust System</MenuItem>
            <MenuItem value={7}>7. Tire & Wheel Repairs</MenuItem>
            <MenuItem value={8}>8. Suspension & Steering Repairs</MenuItem>
            <MenuItem value={9}>9. A/C and Heating System</MenuItem>
            <MenuItem value={10}>10. Fuel System</MenuItem>
            <MenuItem value={11}>11. Windshield and Glass Replacement</MenuItem>
          </TextField>
        </FormControl>

        <FormControl>
          <br />
          <hr />
          <Button
            variant="contained"
            color="info"
            onClick={(e) => saveRepair(e)}
            style={{ marginLeft: "0.5rem" }}
            startIcon={<SaveIcon />}
          >
            Save Repair
          </Button>
        </FormControl>
      </form>
      <hr />
      <Link to="/repair/list">Back to List</Link>
    </Box>
  );
};

export default AddRepair;
