import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import MainPageComponent from './components/main/MainPageComponent';
import BookDetailsComponent from './components/main/BookDetailsComponent';
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
import UpdatePublisherComponent from './components/UpdatePublisherComponent';
import AddPublisherComponent from './components/AddPublisherComponent';
import ListPublisherComponent from './components/ListPublisherComponent';
import UpdateBookComponent from './components/UpdateBookComponent';
import ListUsersComponent from './components/ListUsersComponent';
import Registration from './components/Registration'
import Login from './components/Login'

function App() {
  return (
    <div>
      <Router>
        <div className = "container">
          <HeaderComponent />
            <main className="container">
              <Switch>
                <Route exact path = "/"  component = {MainPageComponent}></Route>
                <Route path = "/books/:id"  component = {BookDetailsComponent}></Route>
                <Route path = "/books" component = {ListBookComponent}></Route>
                <Route path = "/add-book" component = {AddBookComponent}></Route> 
                <Route path = "/update-book/:id" component = {UpdateBookComponent}></Route>   
                <Route path = "/authors" component = {ListAuthorComponent}></Route>   
                <Route path = "/add-author" component = {AddAuthorComponent}></Route>
                <Route path = "/update-author/:id" component = {UpdateAuthorComponent}></Route>
                <Route path = "/categories" component = {ListCategoriesComponent}></Route> 
                <Route path = "/add-category" component = {AddCategoryComponent}></Route>
                <Route path = "/update-category/:id" component = {UpdateCategoryComponent}></Route>
                <Route path = "/publishers" component = {ListPublisherComponent}></Route> 
                <Route path = "/add-publisher" component = {AddPublisherComponent}></Route>
                <Route path = "/update-publisher/:id" component = {UpdatePublisherComponent}></Route>
                <Route path = "/users" component = {ListUsersComponent}></Route>
                <Route path = "/registration" component = {Registration}></Route>
                <Route path = "/login" component = {Login}></Route>
              </Switch>
            </main>
          <FooterComponent />
        </div>
      </Router>
    </div>
    

  );
}

export default App;
