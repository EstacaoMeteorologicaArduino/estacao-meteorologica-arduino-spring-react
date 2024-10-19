import PropTypes from 'prop-types';

import Line from '@ant-design/plots/es/components/line';

const Grafico = ({ dados }) => {
  const { unidade, dadosGrafico } = dados;

  let isMobile = false;

  if (document.body.clientWidth < 768) isMobile = true;

  const menorValor = dadosGrafico.reduce((acc, curr) => (curr.valor < acc ? curr.valor : acc), dadosGrafico[0].valor);
  const maiorValor = dadosGrafico.reduce((acc, curr) => (curr.valor > acc ? curr.valor : acc), dadosGrafico[0].valor);

  const props = {
    data: dadosGrafico,
    shapeField: 'smooth',
    xField: 'hora',
    yField: 'valor',
    tooltip: {
      title: (d) => (d ? d.hora + ':00 horas' : ''),
      items: [{ channel: 'y', valueFormatter: (d) => (d ? d.toFixed(2) + unidade : '') }]
    },
    point: {
      shapeField: 'circle',
      sizeField: 5
    },
    axis: {
      x: { title: 'Hora' },
      y: { title: unidade }
    },
    colorField: 'black',
    forceFit: true,
    style: {
      lineWidth: 2,
      stroke: 'black',
      strokeOpacity: 0.3
    },
    scale: {
      x: {
        domainMin: 0,
        domainMax: 23,
        tickCount: 23
      },
      y: {
        domainMin: menorValor <= 0 ? menorValor : menorValor - 2,
        domainMax: maiorValor <= 0 ? maiorValor + 10 : maiorValor + 1,
        tickCount: dadosGrafico.length
      }
    }
  };

  return (
    <Line
      className="w-ful"
      onReady={(chart) => {
        chart.container.children[0].style.touchAction = 'auto';
      }}
      {...props}
    />
  );
};

Grafico.propTypes = {
  dados: PropTypes.object.isRequired
};

export default Grafico;
