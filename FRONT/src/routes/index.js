import React from 'react';
import { Switch, Route } from 'react-router-dom'
import { Home } from '../pages/Home';
import { Login } from '../pages/Login';


export const Routes = () => (
    <Switch>
        <Route path="/" component={Login} exact/>
        <Route path="/home" component={Home} />
    </Switch>
)