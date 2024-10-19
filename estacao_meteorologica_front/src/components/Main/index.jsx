import { useContext } from 'react';
import Context from '../../context/Context';

import Section from './Section';
import Grafico from './Section/subcomponents/Grafico';

const Main = () => {
  const payload = useContext(Context);
  const grandezas = Object.keys(payload.dados);
  const { dados } = payload;

  return (
    <main>
      {grandezas.map((grandeza, index) => {
        const { mostradores, valores: dadosGrafico, unidade, ultimaMedicao } = dados[grandeza];

        switch (grandeza) {
          case 'temperatura':
            grandeza = 'Temperatura';
            break;
          case 'sensacaoTermica':
            grandeza = 'Sensação Térmica';
            break;
          case 'volumeChuva':
            grandeza = 'Volume de Chuva';
            break;
          case 'umidade':
            grandeza = 'Umidade';
            break;
          case 'pressao':
            grandeza = 'Pressão Atmosférica';
            break;
        }

        return (
          <Section
            key={index}
            title={grandeza}
            grafico={
              <Grafico
                key={index}
                dados={{ unidade, dadosGrafico }}
              />
            }
            dadosMostradores={mostradores}
            ultimaMedicao={ultimaMedicao}
            unidade={unidade}
          />
        );
      })}
    </main>
  );
};

export default Main;
