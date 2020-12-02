import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import FooterComponent from './components/FooterComponent';
import HeaderComponent from './components/HeaderComponent';
import ListBookComponent from './components/ListBookComponent';
import AddBookComponent from './components/AddBookComponent';

function App() {
  return (
    <div>
      <Router>
        <div className = "container">
          <HeaderComponent />
            <main className="container">
              <Switch>
                <Route path = "/books" component = {ListBookComponent}></Route>
                <Route path = "/add-book" component = {AddBookComponent}></Route>
                <ListBookComponent />
              </Switch>
            </main>
          <FooterComponent />
        </div>
      </Router>
    </div>

  );
}

export default App;
