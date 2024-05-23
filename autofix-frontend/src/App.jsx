import "./App.css";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Navbar from "./components/Navbar";
import Home from "./components/Home";
import NotFound from "./components/NotFound";
import CarList from "./components/CarsList";
import EditCar from "./components/EditCar";
import RepairList from "./components/RepairsList";
import RepairDetails from "./components/RepairDetails";
import EditRepair from "./components/EditRepair";
import AddRepair from "./components/AddRepair";
import BonusList from "./components/BonusList";
import AddEditBonus from "./components/AddEditBonus";
import AddCar from "./components/AddCar";
import RepTypeBodywork from "./components/RepTypeBodywork";
import RepTypeEngine from "./components/RepTypeEngine";
import RepAvgRepairTime from "./components/RepAvgRepairTime";

function App() {
  return (
    <Router>
      <div className="container">
        <Navbar></Navbar>
        <Routes>
          <Route path="/home" element={<Home />} />
          <Route path="*" element={<Home />} />
          <Route path="/car/list" element={<CarList />} />
          <Route path="/car/edit/:plate" element={<EditCar />} />
          <Route path="/car/add" element={<AddCar />} />
          <Route path="/repair/list" element={<RepairList />} />
          <Route path="/repair/list/:repairCode" element={<RepairDetails />} />
          <Route path="/repair/edit/:id" element={<EditRepair />} />
          <Route path="/repair/add" element={<AddRepair />} />
          <Route path="/bonus/list" element={<BonusList />} />
          <Route path="/bonus/edit/:id" element={<AddEditBonus />} />
          <Route path="/bonus/add" element={<AddEditBonus />} />
          <Route path="/report/type&bodywork" element={<RepTypeBodywork />} />
          <Route path="/report/type&engine" element={<RepTypeEngine />} />
          <Route path="/report/avgRepairTime" element={<RepAvgRepairTime />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
