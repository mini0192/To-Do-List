import 'bootstrap/dist/css/bootstrap.min.css';

import './App.css'
import {Route, Routes} from "react-router-dom";
import {ToDoFindAll} from "./page/ToDoFindAll.tsx";
import {ToDoSave} from "./page/ToDoSave.tsx";
import {ToDoFindById} from "./page/ToDoFindById.tsx";
import {ToDoSubSave} from "./page/sub/ToDoSubSave.tsx";
import {Layout} from "./Layout.tsx";

function App() {
    return (
        <Routes>
            <Route path="/" element={<Layout/>}>
                <Route path="/" element={<ToDoFindAll/>}/>
                <Route path="/save" element={<ToDoSave/>}/>
                <Route path="/:id" element={<ToDoFindById/>}/>
                <Route path="/subs/save/:parentId" element={<ToDoSubSave/>}/>
            </Route>
        </Routes>
    )
}

export default App
