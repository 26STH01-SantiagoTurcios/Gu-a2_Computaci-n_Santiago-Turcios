
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Map;
import javax.swing.*;

public class jfrmEditor extends JFrame {

    private JTextArea jtxtaEditor = new JTextArea();
    private JFileChooser selector = new JFileChooser();

    public jfrmEditor() {
        setTitle("Editor de Texto");
        setSize(800,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar barra = new JMenuBar();

        JMenu archivo = new JMenu("Archivo");
        JMenu editar = new JMenu("Editar");
        JMenu opciones = new JMenu("Opciones");

        JMenuItem nuevo = new JMenuItem("Nuevo");
        JMenuItem abrir = new JMenuItem("Abrir");
        JMenuItem guardar = new JMenuItem("Guardar");
        JMenuItem salir = new JMenuItem("Salir");

        JMenuItem copiar = new JMenuItem("Copiar");
        JMenuItem cortar = new JMenuItem("Cortar");
        JMenuItem pegar = new JMenuItem("Pegar");

        JMenu color = new JMenu("Color");
        JMenuItem negro = new JMenuItem("Negro");
        JMenuItem rojo = new JMenuItem("Rojo");
        JMenuItem azul = new JMenuItem("Azul");
        JMenuItem verde = new JMenuItem("Verde");

        JMenu estilo = new JMenu("Estilo");
        JMenuItem normal = new JMenuItem("Normal");
        JMenuItem negrita = new JMenuItem("Negrita");
        JMenuItem cursiva = new JMenuItem("Cursiva");

        archivo.add(nuevo);
        archivo.add(abrir);
        archivo.add(guardar);
        archivo.addSeparator();
        archivo.add(salir);

        editar.add(copiar);
        editar.add(cortar);
        editar.add(pegar);

        color.add(negro);
        color.add(rojo);
        color.add(azul);
        color.add(verde);

        estilo.add(normal);
        estilo.add(negrita);
        estilo.add(cursiva);

        opciones.add(color);
        opciones.add(estilo);

        barra.add(archivo);
        barra.add(editar);
        barra.add(opciones);

        setJMenuBar(barra);

        JToolBar toolBar = new JToolBar();
        JButton btnNuevo = new JButton("Nuevo");
        JButton btnAbrir = new JButton("Abrir");
        JButton btnGuardar = new JButton("Guardar");

        toolBar.add(btnNuevo);
        toolBar.add(btnAbrir);
        toolBar.add(btnGuardar);

        add(toolBar, BorderLayout.NORTH);
        add(new JScrollPane(jtxtaEditor), BorderLayout.CENTER);

        nuevo.addActionListener(e -> jtxtaEditor.setText(""));
        btnNuevo.addActionListener(e -> jtxtaEditor.setText(""));

        ActionListener abrirAccion = e -> abrirArchivo();
        abrir.addActionListener(abrirAccion);
        btnAbrir.addActionListener(abrirAccion);

        ActionListener guardarAccion = e -> guardarArchivo();
        guardar.addActionListener(guardarAccion);
        btnGuardar.addActionListener(guardarAccion);

        salir.addActionListener(e -> System.exit(0));

        copiar.addActionListener(e -> jtxtaEditor.copy());
        cortar.addActionListener(e -> jtxtaEditor.cut());
        pegar.addActionListener(e -> jtxtaEditor.paste());

        negro.addActionListener(e -> jtxtaEditor.setForeground(Color.BLACK));
        rojo.addActionListener(e -> jtxtaEditor.setForeground(Color.RED));
        azul.addActionListener(e -> jtxtaEditor.setForeground(Color.BLUE));
        verde.addActionListener(e -> jtxtaEditor.setForeground(Color.GREEN));

        normal.addActionListener(e -> jtxtaEditor.setFont(new Font("Arial", Font.PLAIN, 14)));
        negrita.addActionListener(e -> jtxtaEditor.setFont(new Font("Arial", Font.BOLD, 14)));
        cursiva.addActionListener(e -> jtxtaEditor.setFont(new Font("Arial", Font.ITALIC, 14)));
    }

    private void abrirArchivo() {
        try {
            if(selector.showOpenDialog(this)==JFileChooser.APPROVE_OPTION){
                File f = selector.getSelectedFile();
                BufferedReader br = new BufferedReader(new FileReader(f));
                StringBuilder sb = new StringBuilder();
                String linea;
                while((linea=br.readLine())!=null){
                    sb.append(linea).append("\n");
                }
                br.close();
                jtxtaEditor.setText(sb.toString());
            }
        } catch(Exception ex){
            JOptionPane.showMessageDialog(this,"Error al abrir");
        }
    }

    private void guardarArchivo() {
        try {
            if(selector.showSaveDialog(this)==JFileChooser.APPROVE_OPTION){
                FileWriter fw = new FileWriter(selector.getSelectedFile());
                fw.write(jtxtaEditor.getText());
                fw.close();
                JOptionPane.showMessageDialog(this,"Archivo guardado");
            }
        } catch(Exception ex){
            JOptionPane.showMessageDialog(this,"Error al guardar");
        }
    }

    public static void main(String[] args) {
        new jfrmEditor().setVisible(true);
    }
}
