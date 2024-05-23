const Home = () => {
  return (
    <div>
      <h1>AutoFix Workshop: Repairs System Manager</h1>
      <br />
      <img
        src="../public/161091.svg"
        alt="Logo"
        style={{ height: "130px", marginRight: "10px" }}
      />
      <br />
      <br />
      <p>
        AutoFix, a chain of workshops specialized in the maintenance and repair
        of vehicles (Sedan, Hatchback, SUV, Pickup, and Vans) face management
        challenges efficient of its repair services due to the growing demand
        and the diversity of models and specific repair needs of these vehicles.
        Manual management of repairs have resulted in long wait times for
        customers, difficulties in monitoring the repair history of vehicles,
        resulting in serious customer complaints. To solve these problems and
        improve the quality and efficiency of its services, AutoFix seeks to
        develop a comprehensive repair management system that allows register
        vehicles that arrive at the workshop for the first time, register
        repairs carried out, calculate the total cost of the repairs and
        generate some reports.
      </p>
      <p>
        Web Application developed for managing cars repairs. Technologies used{" "}
        <a href="https://spring.io/projects/spring-boot">Spring Boot</a>{" "}
        (backend), <a href="https://reactjs.org/">React</a> (frontend) y{" "}
        <a href="https://www.postgresql.org/">PostgreSQL</a> (database).
      </p>
    </div>
  );
};
export default Home;
