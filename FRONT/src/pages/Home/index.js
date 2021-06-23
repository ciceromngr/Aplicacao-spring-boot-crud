import './Styled.css';
import React, { useEffect, useState } from 'react';
import { useHistory } from 'react-router-dom';
import api from '../../service/api';
import { getNomeUsuario, logout, getIdUsuario } from '../../service/auth';

export const Home = () => {
    const [data, setData] = useState([]);

    
    const [titulo, setTitulo] = useState("");
    const [nome, setNome] = useState("");
    const [descricao, setDescricao] = useState("");
    
    const history = useHistory();


    const pegarTarefas = async () => {
        const res = await api.get(`/tarefa/doUsuario/${getIdUsuario()}`);
        setData(res.data);
    }
    	
    useEffect(()=> {
        pegarTarefas();
    },[])


    const adicionarTarefa = async (e) => {
        e.preventDefault()
        try {
            if(titulo !== "" && nome !== "" ){

                const params = {
                    "completada": false,
                    "descricaoTarefa": descricao,
                    "nomeTarefa": nome,
                    "tituloTarefa": titulo
                }
                
                await api.post(`/tarefa/adicionar/${getIdUsuario()}`, params);
                
                setDescricao("");
                setNome("");
                setTitulo("");
                pegarTarefas();

            }else {
                alert("Preencha os dados")
            }
            
        } catch (error) {
            throw new Error(error)
        }
    }
    

    const concluir = async (i) => {
        try {
            const params = {
                "completada": true
            }
            await api.put(`/tarefa/atualizar/${i}/${getIdUsuario()}`, params)
            pegarTarefas();
        } catch (error) {
            console.log(error)
        }
    }

    const desconcluir = async (i) => {
        try {
            const params = {
                "completada": false
            }
            await api.put(`/tarefa/atualizar/${i}/${getIdUsuario()}`, params)
            pegarTarefas();
        } catch (error) {
            console.log(error)
        }
    }

    const excluir = async (id) => {
        try {
            await api.delete(`/tarefa/deletar/${id}`)
            pegarTarefas();
        } catch (error) {
            console.log(error)
        }
    }

    function sair(){
        logout();
        history.push("/");
    }

    return (
        <div className="container-dash">
            <div className="card-dash">
                <button className="btn" onClick={sair}>LogOut</button>
                <h1>{getNomeUsuario()}</h1>
                <form  onSubmit={(e)=> adicionarTarefa(e)}>
                    <label>Titulo *</label>
                    <input className="input" type="text" value={titulo} onChange={(e)=> setTitulo(e.target.value)} required/>

                    <label>Nome *</label>
                    <input className="input" type="text" value={nome} onChange={(e)=> setNome(e.target.value)} required/>
                    
                    <label>Descricao</label>
                    <input className="input" type="text" value={descricao} onChange={(e)=> setDescricao(e.target.value)}/>

                    <input type="submit" value="adicionar"/>
                </form> 
            </div>
            <div className="card-body">
                {data && data.map((i)=> (
                    <div key={i.id} className="card-tarefa" style={{background: i.completada? "rgba(19, 163, 38, 0.76)": "#0009"}}>
                        <div className="header">
                            <h1>{i.tituloTarefa}</h1>
                        </div>
                        <div className="body">
                            <button onClick={()=> excluir(i.id)}>X</button>
                            <button className="c" onClick={()=> concluir(i.id)}>C</button>
                            <button className="n" onClick={()=> desconcluir(i.id)}>N</button>
                            <h2>{i.nomeTarefa}</h2>
                            <h3>{i.descricaoTarefa}</h3>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    )
}