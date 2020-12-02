import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import FooterComponent from './components/FooterComponent';
import HeaderComponent from './components/HeaderComponent';
import ListBookComponent from './components/ListBookComponent';
import AddBookComponent from './components/AddBookComponent';
import ListAuthorComponent from './components/ListAuthorComponent';
import ListCategoriesComponent from './components/ListCategoriesComponent';
import AddCategoryComponent from './components/AddCategoryComponent';
import AddAuthorComponent from './components/AddAuthorComponent';
import UpdateCategoryComponent from './components/UpdateCategoryComponent';
import UpdateAuthorComponent from './components/UpdateAuthorComponent';

function App() {
  return (
    <div>
      <Router>
        <div className = "container">
          <HeaderComponent />
            <div className="container">
              <Switch>
                <Route path = "/books" component = {ListBookComponent}></Route>
                <Route path = "/add-book" component = {AddBookComponent}></Route>    
                <Route path = "/authors" component = {ListAuthorComponent}></Route>   
                <Route path = "/add-author" component = {AddAuthorComponent}></Route>
                <Route path = "/update-author/:id" component = {UpdateAuthorComponent}></Route>
                <Route path = "/categories" component = {ListCategoriesComponent}></Route> 
                <Route path = "/add-category" component = {AddCategoryComponent}></Route>
                <Route path = "/update-category/:id" component = {UpdateCategoryComponent}></Route>
              </Switch>
            </div>
          <FooterComponent />
        </div>
      </Router>
    </div>
    

  );
}

export default App;
