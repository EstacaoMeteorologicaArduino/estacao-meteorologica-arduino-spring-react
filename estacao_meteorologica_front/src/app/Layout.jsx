import { useEffect, useState } from 'react';

import Context from '../context/Context';

import { ErrorDisplay } from './../components/ErrorDisplay';
import Header from './../components/Header';
import Main from './../components/Main';

import { Loader } from '../components/Loader';

const Layout = () => {
  const [payload, setPayload] = useState({});
  const [dataDisplay, setDataDisplay] = useState();
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState({ status: false, mensagem: '' });

  const getDataAtual = () => {
    const dataAtual = new Date().toLocaleString('pt-BR');
    return dataAtual.substring(6, 10) + '-' + dataAtual.substring(3, 5) + '-' + dataAtual.substring(0, 2);
  };

  const getDadosPorData = async (data = getDataAtual()) => {
    setIsLoading(true);
    try {
      const response = await fetch(
        `${import.meta.env.VITE_BASE_URL}v1/estacao-meteorologica/resumo-diario?data=${encodeURIComponent(data)}`
      );
      const dados = await response.json();

      if (response.status === 404) {
        throw new Error(dados.mensagem || 'Erro desconhecido');
      }

      setPayload(dados);
      setError({ status: false, mensagem: '' });
    } catch (e) {
      setPayload({});
      setError({ status: true, mensagem: e.message });
    } finally {
      setDataDisplay(data);
      setIsLoading(false);
    }
  };

  useEffect(() => {
    getDadosPorData();
  }, []);

  return (
    <Context.Provider value={payload}>
      {isLoading ? (
        <div className="w-full h-screen flex items-center justify-center text-8xl mt-[-32px]">
          <Loader />
        </div>
      ) : (
        <>
          <Header
            handleMudaData={getDadosPorData}
            getDataAtual={getDataAtual}
            dataDisplay={dataDisplay}
          />
          {error.status ? <ErrorDisplay messagem={error.mensagem} /> : <Main />}
        </>
      )}
    </Context.Provider>
  );
};

export default Layout;
